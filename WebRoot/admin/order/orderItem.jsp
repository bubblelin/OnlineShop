<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<table border="0" width="100%">
<s:iterator value="oiList" var="orderItem">
	<tr>
		<td><img alt="图片" width="40" height="45"  src="${pageContext.request.contextPath}/<s:property value="#orderItem.product.image"/>"></td>
	</tr>
	<tr>
		<td>数量：<s:property value="#orderItem.count"/></td>
	</tr>
		<td>小计：<s:property value="#orderItem.subtotal"/></td>
	<tr>
</s:iterator>
</table>