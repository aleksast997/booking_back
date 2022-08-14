/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.ac.silab.BookingApi.controller;

import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.fon.ac.silab.BookingApi.domain.CheckoutPayment;

/**
 *
 * @author aleks
 */

@RestController
@RequestMapping(value = "/api")
public class StripeController {
    
    private static Gson gson = new Gson();
    
    @CrossOrigin
    @PostMapping("payment")
    public String paymentWithCheckoutPage(@RequestBody CheckoutPayment payment) throws StripeException{
        init();
        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(payment.getSuccessUrl())
                .setCancelUrl(payment.getCancelUrl())
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(payment.getQuantity())
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency(payment.getCurrency())
                                                .setUnitAmount(payment.getAmount())
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                        .setName(payment.getName()).build()
                                                ).build()
                                ).build()
                ).build();
        Session session = Session.create(params);
        PaymentIntentCreateParams p = PaymentIntentCreateParams.builder()
        .setAmount(session.getAmountTotal())
        .setCurrency(session.getCurrency())
        .addPaymentMethodType("card")
        .build();

    PaymentIntent paymentIntent = PaymentIntent.create(p);
        
        Map<String, String> responseData = new HashMap<>();
        responseData.put("id", session.getId());
        responseData.put("clientReferencedId", session.getClientReferenceId());
        responseData.put("client_secret", paymentIntent.getClientSecret());
        System.out.println(responseData);
        
        return gson.toJson(responseData);
    }
    
    private static void init() {
        Stripe.apiKey = "sk_test_51KpyvmKLZeRwfurBe2nXzHYafHinXeuQvETk7RA5Y5C81aMyhGzS1mA2DzSyVnVRZBqf31xbs5Cw1YahDJzP0bDm00OtAvbV4W";
    }
    
}
