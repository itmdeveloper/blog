<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
	<input type="hidden" id="id" value="${principal.user.id }">
		<div class="form-group">
			<label for="username">UserName</label> 
			<input value="${principal.user.username }" readonly="readonly" type="text" class="form-control" placeholder="Enter username" id="username">
		</div>
		<c:if test="${empty principal.user.oauth}">
			<div class="form-group">
				<label for="password">Password</label> 
				<input type="password" class="form-control" placeholder="Enter password" id="password">
			</div>
		</c:if>
		<div class="form-group">
			<label for="email">Email</label> 
			<input readonly="readonly" value="${principal.user.email}" type="email" class="form-control" placeholder="Enter email" id="email">
		</div>
	</form>
	<button id="btn-update" class="btn btn-primary">회원정보수정완료</button>
</div>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>



