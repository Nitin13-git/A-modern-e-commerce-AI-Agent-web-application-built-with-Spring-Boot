package com.Ecommerce.webApp.prompt;

import java.util.HashMap;
import java.util.Map;

public class EcommerceRouterPrompt {
    public static final String DEFAULT_ROUTER_PROMPT = """
            You are a helpful AI assistant for an e-commerce system. Your goal is to help users interact with the e-commerce platform through natural language.

            Here are the available tools and their purposes:
            %s

            Follow these rules when handling user queries:
            1. For product-related queries:
               - If user asks to see all products or list products, use getAllProducts
               - If user asks to search for specific products, use searchProducts with appropriate keywords
               - If user asks for details of a specific product, use getProductById
               - If user wants to create a new product, use createProduct
               - If user wants to update a product, use updateProduct
               - If user wants to delete a product, use deleteProduct

            2. For order-related queries:
               - If user wants to place an order, use createOrder
               - If user wants to view their orders, use getOrders
               - If user wants to update an order, use updateOrder
               - If user wants to cancel an order, use deleteOrder

            3. For general queries:
               - If user asks about system status or help, provide appropriate guidance
               - If user's query doesn't match any specific tool, ask for clarification
               - Always be polite and helpful

            Always provide clear and concise responses based on the tool outputs.
            If you're unsure about how to handle a query, ask for clarification.
            """;

    // Tool Descriptions
    public static final String PRODUCT_TOOLS_DESC = """
        Product Management Tools:
        - getAllProducts: Retrieves a list of all available products
        - searchProducts: Searches for products matching given keywords
        - getProductById: Gets detailed information about a specific product
        - createProduct: Creates a new product in the system
        - updateProduct: Updates an existing product's information
        - deleteProduct: Removes a product from the system
        """;

    public static final String ORDER_TOOLS_DESC = """
        Order Management Tools:
        - createOrder: Creates a new order for products
        - getOrders: Retrieves a list of orders
        - updateOrder: Updates an existing order
        - deleteOrder: Cancels an existing order
        """;

    // Map of tool names to their descriptions
    private static final Map<String, String> TOOL_DESCRIPTIONS = new HashMap<>();
    
    static {
        TOOL_DESCRIPTIONS.put("getAllProducts", "Retrieves a list of all available products");
        TOOL_DESCRIPTIONS.put("searchProducts", "Searches for products matching given keywords");
        TOOL_DESCRIPTIONS.put("getProductById", "Gets detailed information about a specific product");
        TOOL_DESCRIPTIONS.put("createProduct", "Creates a new product in the system");
        TOOL_DESCRIPTIONS.put("updateProduct", "Updates an existing product's information");
        TOOL_DESCRIPTIONS.put("deleteProduct", "Removes a product from the system");
        TOOL_DESCRIPTIONS.put("createOrder", "Creates a new order for products");
        TOOL_DESCRIPTIONS.put("getOrders", "Retrieves a list of orders");
        TOOL_DESCRIPTIONS.put("updateOrder", "Updates an existing order");
        TOOL_DESCRIPTIONS.put("deleteOrder", "Cancels an existing order");
    }

    public static String getToolDescription(String toolName) {
        return TOOL_DESCRIPTIONS.get(toolName);
    }

    public static Map<String, String> getAllToolDescriptions() {
        return TOOL_DESCRIPTIONS;
    }
} 