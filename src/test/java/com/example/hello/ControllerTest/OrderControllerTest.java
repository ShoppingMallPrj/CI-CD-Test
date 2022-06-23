package com.example.hello.ControllerTest;

//import com.example.hello.Dto.Common.OrderDto;
//import com.example.hello.Dto.Common.OrderListDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;


    //Vaild 조건에 걸려 실패한다.
    @Test
    void orderTestFail() throws Exception {

//        OrderDto orderDto = new OrderDto();
//        Set<OrderListDto> orderList = new HashSet<>();
//        orderDto.setOrderList(orderList);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        this.mockMvc.perform(post("/api/order/order")
//                        .content(objectMapper.writeValueAsString(orderDto))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(status().is4xxClientError());
    }

    @Test
    void orderTestSuccess() throws Exception {

//        OrderDto orderDto = new OrderDto();
//
//        Set<OrderListDto> orderList = new HashSet<>();
//        OrderListDto orderListDto = new OrderListDto();
//        orderListDto.setItemStock(2);
//        orderListDto.setItemId(1);
//        orderList.add(orderListDto);
//
//        orderDto.setOrderList(orderList);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        this.mockMvc.perform(post("/api/order/order")
//                        .content(objectMapper.writeValueAsString(orderDto))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(status().is2xxSuccessful());
    }
}
