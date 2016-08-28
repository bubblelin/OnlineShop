<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<title>我的订单</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css"/>

</head>
<body>

<div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="https://bubblelin.github.io/">
				<img src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.gif" alt="京东商城"/>
			</a>
		</div>
	</div>
	<div class="span9">
<div class="headerAd">
	<img src="${pageContext.request.contextPath}/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障"/>
</div>	
</div>
	
	<!-- menu页面开始 -->
	<%@ include file="menu.jsp" %>
	<!-- menu页面借宿 -->
	
</div>	

<div class="container cart">

	<div class="span24">
	
		<div class="step step1">
			<ul>
				<li  class="current"></li>
				<li><h2 style="color: orange;">我的订单</h2></li>
			</ul>
		</div>
		
	<table>
		<tbody>
		<s:iterator value="pageBean.listData" var="order">
			<tr>
				<th colspan="5">订单编号：<s:property value="#order.oid"/>
				订单状态：
				<s:if test="#order.state == 1">
					<a href="${pageContext.request.contextPath}/order_findByOid.action?oid=<s:property value="#order.oid"/>"><font color="red">未付款，去付款</font></a>
				</s:if>
				<s:if test="#order.state == 2">
					已经付款，正在发货
				</s:if>
				<s:if test="#order.state == 3">
					<a href="${pageContext.request.contextPath}/order_updateState.action?oid=<s:property value="#order.oid"/>"><font color="red">确认收货</font></a>
				</s:if>
				<s:if test="#order.state == 4">
					交易完成
				</s:if>
				</th>
			</tr>
			<tr>
				<th>图片</th>
				<th>商品</th>
				<th>价格</th>
				<th>数量</th>
				<th>小计</th>
			</tr>
			
			<s:iterator value="#order.orderItemSet" var="orderItem">
				<tr>
					<td width="60">
						<input type="hidden" name="id" value="22"/>
						<img src="${pageContext.request.contextPath}/<s:property value="#orderItem.product.image"/>"/>
					</td>
					<td>
						<a target="_blank"><s:property value="product.pname"/></a>
					</td>
					<td>
						<s:property value="#orderItem.product.shop_price"/>
					</td>
					<td class="quantity" width="60">
						<s:property value="#orderItem.count"/>
					</td>
					<td width="140">
						<span class="subtotal">￥<s:property value="#orderItem.subtotal"/></span>
					</td>
				</tr>
			</s:iterator>
		</s:iterator>
			<tr>
				<td colspan="5">
					<div class="pagination">
							<span>当前第<s:property value="pageBean.currentPage"/>/<s:property value="pageBean.totalPage"/>页</span> 
						
							<s:if test="pageBean.currentPage != 1">
								<a href="${pageContext.request.contextPath}/order_findByUid.action?currentPage=1" class="firstPage">&nbsp;</a>
								<a href="${pageContext.request.contextPath}/order_findByUid.action?currentPage=<s:property value="pageBean.currentPage-1"/>" class="previousPage">&nbsp;</a>
							</s:if>	
							
							<s:iterator begin="1" end="pageBean.totalPage" var="i">
								<s:if test="pageBean.currentPage != #i">
									<a href="${pageContext.request.contextPath}/order_findByUid.action?currentPage=<s:property value="#i"/>"><s:property value="#i"/></a>
								</s:if>
								<s:else>
									<span class="currentPage"><s:property value="#i"/></span>
								</s:else>
							</s:iterator>
							
							<s:if test="pageBean.currentPage != pageBean.totalPage">
								<a class="nextPage" href="${pageContext.request.contextPath}/order_findByUid.action?currentPage=<s:property value="pageBean.currentPage+1"/>">&nbsp;</a>
								<a class="lastPage" href="${pageContext.request.contextPath}/order_findByUid.action?currentPage=<s:property value="pageBean.totalPage"/>">&nbsp;</a>
							</s:if>
					</div>
				</td>
			</tr>
		</tbody>
	</table>
		
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="image\r___________renleipic_01/footer.jpg" alt="我们的优势" title="我们的优势" height="52" width="950">
</div>
</div>
	<div class="span24">
		<ul class="bottomNav">
					<li>
						<a href="#">关于我们</a>
						|
					</li>
					<li>
						<a href="#">联系我们</a>
						|
					</li>
					<li>
						<a href="#">诚聘英才</a>
						|
					</li>
					<li>
						<a href="#">法律声明</a>
						|
					</li>
					<li>
						<a>友情链接</a>
						|
					</li>
					<li>
						<a target="_blank">支付方式</a>
						|
					</li>
					<li>
						<a target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a >SHOP++官网</a>
						|
					</li>
					<li>
						<a>SHOP++论坛</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2005-2015 网上商城 版权所有</div>
	</div>
</div>
</body>
</html>
