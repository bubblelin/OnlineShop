<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0048)http://localhost:8080/mango/product/list/1.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>京东商城</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css"/>

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
					<img src="${pageContext.request.contextPath}/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障">
</div>	
</div>

	<!-- menu页面开始 -->
	<%@ include file="menu.jsp" %>
	<!-- menu页面借宿 -->
	
</div>	
<div class="container productList">
		<div class="span6">
			<div class="hotProductCategory">
				<s:iterator value="#session.cList" var="c">
						<dl>
							<dt>
								<a href="${pageContext.request.contextPath}/product_findByCid.action?cid=<s:property value="#c.cid"/>&currentPage=1"><s:property value="#c.cname"/></a>
							</dt>
								<s:iterator value="#c.categorySecondSet" var="cs">
									<dd>
										<a href="${pageContext.request.contextPath}/product_findByCsid.action?csid=<s:property value="#cs.csid"/>&currentPage=1"><s:property value="#cs.csname"/></a>
									</dd>
								</s:iterator>
						</dl>
				</s:iterator>
			</div>
		</div>
		<div class="span18 last">
			
			<form id="productForm" action="${pageContext.request.contextPath}/image/蔬菜 - Powered By Mango Team.htm" method="get">
					
				<div id="result" class="result table clearfix">
					<ul>
						<s:iterator value="pageBean.listData" var="p">
							<li>
								<a href="${pageContext.request.contextPath}/product_findByPid.action?pid=<s:property value="#p.pid"/>">
									<img src="${pageContext.request.contextPath}/<s:property value="#p.image"/>" width="170" height="170"  style="display: inline-block;">
									   
									<span style='color:green'>
									 <s:property value="#p.pname"/>
									</span>
									 
									<span class="price">
										商城价： ￥<s:property value="#p.shop_price"/>/份
									</span>
									 
								</a>
							</li>
						</s:iterator>	
					</ul>
				</div>
				<div class="pagination">
						<span>当前第<s:property value="pageBean.currentPage"/>/<s:property value="pageBean.totalPage"/>页</span> 
					<s:if test="cid != null">
						<s:if test="pageBean.currentPage != 1">
							<a href="${pageContext.request.contextPath}/product_findByCid.action?cid=<s:property value="cid"/>&currentPage=1" class="firstPage">&nbsp;</a>
							<a href="${pageContext.request.contextPath}/product_findByCid.action?cid=<s:property value="cid"/>&currentPage=<s:property value="pageBean.currentPage-1"/>" class="previousPage">&nbsp;</a>
						</s:if>	
						<s:iterator begin="1" end="pageBean.totalPage" var="i">
							<s:if test="pageBean.currentPage != #i">
								<a href="${pageContext.request.contextPath}/product_findByCid.action?cid=<s:property value="cid"/>&currentPage=<s:property value="#i"/>"><s:property value="#i"/></a>
							</s:if>
							<s:else>
								<span class="currentPage"><s:property value="#i"/></span>
							</s:else>
						</s:iterator>
						<s:if test="pageBean.currentPage != pageBean.totalPage">
							<a class="nextPage" href="${pageContext.request.contextPath}/product_findByCid.action?cid=<s:property value="cid"/>&currentPage=<s:property value="pageBean.currentPage+1"/>">&nbsp;</a>
							<a class="lastPage" href="${pageContext.request.contextPath}/product_findByCid.action?cid=<s:property value="cid"/>&currentPage=<s:property value="pageBean.totalPage"/>">&nbsp;</a>
						</s:if>
					</s:if>
					<s:if test="csid != null">
						<s:if test="pageBean.currentPage != 1">
							<a href="${pageContext.request.contextPath}/product_findByCsid.action?csid=<s:property value="csid"/>&currentPage=1" class="firstPage">&nbsp;</a>
							<a href="${pageContext.request.contextPath}/product_findByCsid.action?csid=<s:property value="csid"/>&currentPage=<s:property value="pageBean.currentPage-1"/>" class="previousPage">&nbsp;</a>
						</s:if>	
						<s:iterator begin="1" end="pageBean.totalPage" var="i">
							<s:if test="pageBean.currentPage != #i">
								<a href="${pageContext.request.contextPath}/product_findByCsid.action?csid=<s:property value="csid"/>&currentPage=<s:property value="#i"/>"><s:property value="#i"/></a>
							</s:if>
							<s:else>
								<span class="currentPage"><s:property value="#i"/></span>
							</s:else>
						</s:iterator>
						<s:if test="pageBean.currentPage != pageBean.totalPage">
							<a class="nextPage" href="${pageContext.request.contextPath}/product_findByCsid.action?csid=<s:property value="csid"/>&currentPage=<s:property value="pageBean.currentPage+1"/>">&nbsp;</a>
							<a class="lastPage" href="${pageContext.request.contextPath}/product_findByCsid.action?csid=<s:property value="csid"/>&currentPage=<s:property value="pageBean.totalPage"/>">&nbsp;</a>
						</s:if>
					</s:if>
						
				</div>
			</form>
		</div>
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="${pageContext.request.contextPath}/image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势">
</div>	</div>
	<div class="span24">
		<ul class="bottomNav">
					<li>
						<a >关于我们</a>
						|
					</li>
					<li>
						<a>联系我们</a>
						|
					</li>
					<li>
						<a >诚聘英才</a>
						|
					</li>
					<li>
						<a >法律声明</a>
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
						<a  target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a >官网</a>
						|
					</li>
					<li>
						<a >论坛</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright©2005-2015 网上商城 版权所有</div>
	</div>
</div>
</body></html>