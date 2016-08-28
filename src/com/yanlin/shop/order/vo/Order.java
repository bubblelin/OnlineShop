package com.yanlin.shop.order.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yanlin.shop.user.vo.User;

/**
 * 订单order的实体类
 * @author bubblelin
 *CREATE TABLE `orders` (
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `total` double DEFAULT NULL,
  `ordertime` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `addr` varchar(50) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `FKC3DF62E5AA3D9C7` (`uid`),
  CONSTRAINT `FKC3DF62E5AA3D9C7` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=9004 DEFAULT CHARSET=utf8;
 */
public class Order implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer oid;
	private double total;
	private Date ordertime;
	private Integer state;
	private String name;
	private String phone;
	private String addr;
	//订单所属关联的用户
	private User user;
	//订单所属关联的订单项
	private Set<OrderItem> orderItemSet = new HashSet<OrderItem>();
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<OrderItem> getOrderItemSet() {
		return orderItemSet;
	}
	public void setOrderItemSet(Set<OrderItem> orderItemSet) {
		this.orderItemSet = orderItemSet;
	}
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", total=" + total + ", ordertime="
				+ ordertime + ", state=" + state + ", name=" + name
				+ ", phone=" + phone + ", addr=" + addr + ", user=" + user
				+ ", orderItemSet=" + orderItemSet + "]";
	}
	
	
}
