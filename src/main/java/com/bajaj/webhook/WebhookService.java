package com.bajaj.webhook;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Service
public class WebhookService {

    private final RestTemplate restTemplate = new RestTemplate();
    
    @EventListener(ApplicationReadyEvent.class)
    public void startProcess() {
        try {
            System.out.println("🚀 Starting Bajaj Process...");
            
            // Step 1: Get webhook
            WebhookResponse response = generateWebhook();
            
            // Step 2: Your SQL solution (94 = even = Question 2)
            String sqlQuery = getSqlSolution();
            
            // Step 3: Submit solution
            submitSolution(response.getWebhook(), response.getAccessToken(), sqlQuery);
            
            System.out.println("✅ DONE! Check your results!");
            
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private WebhookResponse generateWebhook() throws Exception {
        String url = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = new HashMap<>();
        body.put("name", "Sahana R");
        body.put("regNo", "1RF22CS094");
        body.put("email", "sahanaraghu49@gmail.com");

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
        
        ResponseEntity<WebhookResponse> response = restTemplate.postForEntity(url, request, WebhookResponse.class);
        
        System.out.println("📡 Webhook: " + response.getBody().getWebhook());
        return response.getBody();
    }

    private String getSqlSolution() {
        // Your regNo 1RF22CS094 -> last digits 94 -> EVEN -> Question 2
        System.out.println("📊 Solving Question 2 (Even regNo)");
        
        return "SELECT e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME, " +
               "COUNT(e2.EMP_ID) AS YOUNGER_EMPLOYEES_COUNT " +
               "FROM EMPLOYEE e1 " +
               "JOIN DEPARTMENT d ON e1.DEPARTMENT = d.DEPARTMENT_ID " +
               "LEFT JOIN EMPLOYEE e2 ON e1.DEPARTMENT = e2.DEPARTMENT AND e2.DOB > e1.DOB " +
               "GROUP BY e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME " +
               "ORDER BY e1.EMP_ID DESC";
    }

    private void submitSolution(String webhookUrl, String accessToken, String sqlQuery) throws Exception {
        String url = "https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA";
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        Map<String, String> body = new HashMap<>();
        body.put("finalQuery", sqlQuery);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
        
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        
        System.out.println("📤 Submitted! Status: " + response.getStatusCode());
        System.out.println("Response: " + response.getBody());
    }
}