package com.iesvdm.shopping_cart.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.iesvdm.shopping_cart.dto.CheckoutDto;
import com.iesvdm.shopping_cart.model.Coupon;
import com.iesvdm.shopping_cart.model.CustomerOrder;
import com.iesvdm.shopping_cart.model.OrderItem;
import com.iesvdm.shopping_cart.service.CouponService;
import com.iesvdm.shopping_cart.service.OrderService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/cart")
@SessionAttributes("cart")
@RequiredArgsConstructor
public class ShoppingController {

    private final OrderService orderService;
    private final CouponService couponService;
    private final JdbcTemplate jdbcTemplate;

    @ModelAttribute("cart")
    public List<OrderItem> cart() {
        return new ArrayList<>();
    }

    @GetMapping("")
    public String viewCart(@ModelAttribute("cart") List<OrderItem> cart, Model model) {
        model.addAttribute("cart", cart);
        return "cart";
    }

    @PostMapping("/add")
    public String addToCart(@ModelAttribute OrderItem item,
                            @ModelAttribute("cart") List<OrderItem> cart) {
        // Calcular line_total si no viene calculado: lineTotal = unitPrice * quantity
        if (item.getLineTotal() == null && item.getUnitPrice() != null && item.getQuantity() != null) {
            BigDecimal lineTotal = item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            item.setLineTotal(lineTotal);
        }
        cart.add(item);
        return "redirect:/cart";
    }

    @PostMapping("/remove/{index}")
    public String removeFromCart(@PathVariable int index,
                                 @ModelAttribute("cart") List<OrderItem> cart) {
        if (index >= 0 && index < cart.size()) {
            cart.remove(index);
        }
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String showCheckoutForm(@ModelAttribute("cart") List<OrderItem> cart, Model model) {
        if (cart.isEmpty()) {
            return "redirect:/cart";
        }

        // Calcular totales para el resumen
        BigDecimal grossTotal = cart.stream()
                .map(item -> item.getLineTotal() != null ? item.getLineTotal() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Obtener cupones activos para mostrar al usuario
        List<Coupon> activeCoupons = couponService.findActiveCoupons();

        // Agregar datos al modelo para la vista
        model.addAttribute("cart", cart);
        model.addAttribute("grossTotal", grossTotal);
        model.addAttribute("discountTotal", BigDecimal.ZERO);
        model.addAttribute("finalTotal", grossTotal);
        model.addAttribute("checkoutDto", new CheckoutDto());
        model.addAttribute("activeCoupons", activeCoupons);

        return "checkout";
    }

    @PostMapping("/checkout")
    public String processCheckout(@ModelAttribute("cart") List<OrderItem> cart,
                                   @ModelAttribute CheckoutDto checkoutDto,
                                   SessionStatus sessionStatus,
                                   Model model) {
        if (cart.isEmpty()) {
            return "redirect:/cart";
        }

        // Calcular el total bruto usando lambda: suma de todos los lineTotals
        BigDecimal grossTotal = cart.stream()
                .map(item -> item.getLineTotal() != null ? item.getLineTotal() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Aplicar cupón de descuento si existe
        BigDecimal discountTotal = BigDecimal.ZERO;
        Coupon appliedCoupon = null;

        if (checkoutDto.getCouponCode() != null && !checkoutDto.getCouponCode().trim().isEmpty()) {
            var couponOpt = couponService.findValidCouponByCode(checkoutDto.getCouponCode().trim().toUpperCase());

            if (couponOpt.isPresent()) {
                appliedCoupon = couponOpt.get();
                discountTotal = couponService.calculateDiscount(appliedCoupon, grossTotal);
            } else {
                // Cupón no válido - volver al formulario con error
                List<Coupon> activeCoupons = couponService.findActiveCoupons();
                model.addAttribute("cart", cart);
                model.addAttribute("grossTotal", grossTotal);
                model.addAttribute("discountTotal", BigDecimal.ZERO);
                model.addAttribute("finalTotal", grossTotal);
                model.addAttribute("checkoutDto", checkoutDto);
                model.addAttribute("activeCoupons", activeCoupons);
                model.addAttribute("couponError", "El cupón '" + checkoutDto.getCouponCode().toUpperCase() + "' no es válido, ha expirado o no está activo. Por favor, verifica los cupones disponibles.");
                return "checkout";
            }
        }

        // Calcular el total final
        BigDecimal finalTotal = couponService.calculateFinalTotal(grossTotal, discountTotal);

        // Si el usuario marcó "misma dirección", copiar datos de facturación a envío
        if (checkoutDto.isSameAsbilling()) {
            checkoutDto.setShippingName(checkoutDto.getBillingName());
            checkoutDto.setShippingStreet(checkoutDto.getBillingStreet());
            checkoutDto.setShippingCity(checkoutDto.getBillingCity());
            checkoutDto.setShippingPostalCode(checkoutDto.getBillingPostalCode());
            checkoutDto.setShippingCountry(checkoutDto.getBillingCountry());
        }

        // Crear la orden con número de orden temporal y datos del formulario
        LocalDateTime now = LocalDateTime.now();
        CustomerOrder order = CustomerOrder.builder()
                .orderNumber("TEMP")
                .status("NEW")
                .grossTotal(grossTotal)
                .discountTotal(discountTotal)
                .finalTotal(finalTotal)
                .createdAt(now)
                // Datos de pago
                .paymentMethod(checkoutDto.getPaymentMethod())
                .paymentStatus("PENDING")
                .paymentCountry(checkoutDto.getPaymentCountry())
                // Datos de facturación
                .billingName(checkoutDto.getBillingName())
                .billingTaxId(checkoutDto.getBillingTaxId())
                .billingStreet(checkoutDto.getBillingStreet())
                .billingCity(checkoutDto.getBillingCity())
                .billingPostalCode(checkoutDto.getBillingPostalCode())
                .billingCountry(checkoutDto.getBillingCountry())
                // Datos de envío
                .shippingName(checkoutDto.getShippingName())
                .shippingStreet(checkoutDto.getShippingStreet())
                .shippingCity(checkoutDto.getShippingCity())
                .shippingPostalCode(checkoutDto.getShippingPostalCode())
                .shippingCountry(checkoutDto.getShippingCountry())
                .build();

        // Guardar la orden en la base de datos para obtener el ID
        CustomerOrder savedOrder = orderService.create(order);

        // Generar número de orden con formato: ORD-YYYYMMDD-XXXX
        String orderNumber = generateOrderNumber(savedOrder.getId(), now);

        // Actualizar el número de orden en la base de datos
        jdbcTemplate.update(
            "UPDATE customer_order SET order_number = ? WHERE id = ?",
            orderNumber,
            savedOrder.getId()
        );
        savedOrder.setOrderNumber(orderNumber);

        // Guardar cada item del carrito usando JDBC y lambda forEach
        cart.forEach(item ->
            jdbcTemplate.update(
                "INSERT INTO order_item (order_id, product_name, unit_price, quantity, product_id) VALUES (?, ?, ?, ?, ?)",
                savedOrder.getId(),
                item.getProductName(),
                item.getUnitPrice(),
                item.getQuantity(),
                item.getProductId()
            )
        );

        // Limpiar el carrito y completar la sesión
        cart.clear();
        sessionStatus.setComplete();

        return "redirect:/cart/orders";
    }

    /**
     * Genera un número de orden con formato: ORD-YYYYMMDD-XXXX
     * Ejemplo: ORD-20251211-0001
     *
     * @param orderId ID de la orden (se usa como número secuencial)
     * @param dateTime Fecha y hora de la orden
     * @return Número de orden formateado
     */
    private String generateOrderNumber(Long orderId, LocalDateTime dateTime) {
        // Formatear la fecha como YYYYMMDD
        String datePart = dateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        // Formatear el ID como número de 4 dígitos con ceros a la izquierda
        String idPart = String.format("%04d", orderId);

        // Combinar: ORD-YYYYMMDD-XXXX
        return String.format("ORD-%s-%s", datePart, idPart);
    }

    @GetMapping("/orders")
    public String listOrders(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "orders";
    }

    /**
     * Endpoint temporal de debug para verificar cupones
     * Acceder a: /cart/debug-coupons
     */
    @GetMapping("/debug-coupons")
    @ResponseBody
    public String debugCoupons() {
        StringBuilder sb = new StringBuilder();
        sb.append("<h1>Debug: Cupones en la Base de Datos</h1>");
        sb.append("<h2>Todos los cupones:</h2>");

        List<Coupon> allCoupons = jdbcTemplate.query(
            "SELECT * FROM coupon",
            (rs, rowNum) -> {
                Coupon c = new Coupon();
                c.setId(rs.getInt("id"));
                c.setCode(rs.getString("code"));
                c.setDescription(rs.getString("description"));
                c.setDiscountType(Coupon.DiscountType.valueOf(rs.getString("discount_type")));
                c.setDiscountValue(rs.getBigDecimal("discount_value"));
                c.setActive(rs.getBoolean("active"));
                c.setValidFrom(rs.getTimestamp("valid_from") != null ? rs.getTimestamp("valid_from").toLocalDateTime() : null);
                c.setValidTo(rs.getTimestamp("valid_to") != null ? rs.getTimestamp("valid_to").toLocalDateTime() : null);
                return c;
            }
        );

        sb.append("<table border='1' style='border-collapse: collapse; margin: 20px 0;'>");
        sb.append("<tr><th>ID</th><th>Código</th><th>Descripción</th><th>Tipo</th><th>Valor</th><th>Activo</th><th>Válido Desde</th><th>Válido Hasta</th></tr>");

        for (Coupon c : allCoupons) {
            sb.append("<tr>");
            sb.append("<td>").append(c.getId()).append("</td>");
            sb.append("<td><strong>").append(c.getCode()).append("</strong></td>");
            sb.append("<td>").append(c.getDescription()).append("</td>");
            sb.append("<td>").append(c.getDiscountType()).append("</td>");
            sb.append("<td>").append(c.getDiscountValue()).append("</td>");
            sb.append("<td>").append(c.getActive() ? "✅ SI" : "❌ NO").append("</td>");
            sb.append("<td>").append(c.getValidFrom()).append("</td>");
            sb.append("<td>").append(c.getValidTo()).append("</td>");
            sb.append("</tr>");
        }
        sb.append("</table>");

        sb.append("<h2>Cupones activos encontrados por el servicio:</h2>");
        List<Coupon> activeCoupons = couponService.findActiveCoupons();
        sb.append("<p>Total de cupones activos: ").append(activeCoupons.size()).append("</p>");

        if (activeCoupons.isEmpty()) {
            sb.append("<p style='color: red;'><strong>⚠️ No se encontraron cupones activos. Este es el problema!</strong></p>");
        } else {
            sb.append("<ul>");
            for (Coupon c : activeCoupons) {
                sb.append("<li><strong>").append(c.getCode()).append("</strong> - ").append(c.getDescription()).append("</li>");
            }
            sb.append("</ul>");
        }

        sb.append("<h2>Fecha actual del servidor:</h2>");
        sb.append("<p>").append(LocalDateTime.now()).append("</p>");

        sb.append("<h2>Consulta SQL utilizada:</h2>");
        sb.append("<pre>SELECT * FROM coupon WHERE active = TRUE AND NOW() BETWEEN valid_from AND valid_to</pre>");

        sb.append("<hr><p><a href='/cart'>Volver al carrito</a></p>");

        return sb.toString();
    }
}
