package com.cloudage.membercenter.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudage.membercenter.entity.Goods;
import com.cloudage.membercenter.entity.Orders;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.service.IArticleService;
import com.cloudage.membercenter.service.ICommentService;
import com.cloudage.membercenter.service.IGoodsService;
import com.cloudage.membercenter.service.ILikesService;
import com.cloudage.membercenter.service.IOrdersService;
import com.cloudage.membercenter.service.IUserService;

@RestController
@RequestMapping("/api")
public class OrderController {
	  @Autowired
      IUserService userService;

      @Autowired
      IGoodsService goodsService;

      @Autowired
      IOrdersService ordersService;
      
      @Autowired
 	 UserController userController;
      
     
      
   // 生成订单
      @RequestMapping(value = "/goods/{goods_id}/orders", method = RequestMethod.POST)
      public Orders addOrders(@RequestParam int ordersID, @RequestParam int ordersState,
                      @RequestParam String goodsQTY, @RequestParam String goodsSum, @RequestParam String buyerName,
                      @RequestParam String buyerPhoneNum, @RequestParam String buyerAddress,
                      @RequestParam String paySum, @PathVariable int goods_id, HttpServletRequest request) {

              User me = userController.getCurrentUser(request);
              Goods goods = goodsService.findById(goods_id);
              
              if (ordersState == 1) {
                      Orders orders = new Orders();
                      orders.setOrdersID(ordersID);
                      orders.setOrdersState(ordersState);
                      orders.setBuyer(me);
                      orders.setBuyerName(buyerName);
                      orders.setBuyerPhoneNum(buyerPhoneNum);
                      orders.setBuyerAddress(buyerAddress);
                      orders.setGoods(goods);
                      orders.setGoodsQTY(goodsQTY);
                      orders.setGoodsSum(goodsSum);
                      return ordersService.save(orders);
              }
              if (ordersState == 2) {
                      Orders orders = ordersService.findOrdersByOrdersID(ordersID); 
                      orders.setOrdersState(ordersState);
                      orders.setPaySum(paySum);
                      return ordersService.save(orders);
              } else {
                      Orders orders = ordersService.findOrdersByOrdersID(ordersID); 
                      orders.setOrdersState(ordersState);
                      return ordersService.save(orders);
              }
      }

      @RequestMapping("/ordersOfSeller")
      public Page<Orders> getOrdersOfSeller(@RequestParam(defaultValue = "0") int page, HttpServletRequest request) {
              User me = userController.getCurrentUser(request);
              return ordersService.findAllBySellerId(me.getId(), page);
      }

      @RequestMapping("/ordersOfBuyer")
      public Page<Orders> getOrdersOfBuyer(@RequestParam(defaultValue = "0") int page, HttpServletRequest request) {
              User me = userController.getCurrentUser(request);
              return ordersService.findAllByBuyerId(me.getId(), page);
      }

      @RequestMapping("/deleteOrders")
      public void deleteOrders(@RequestParam int ordersID , HttpServletRequest request) {
              Orders orders = ordersService.findOrdersByOrdersID(ordersID);
              ordersService.deleteOrders(orders);
      }
      
      


}
