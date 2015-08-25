<%@ include file="/WEB-INF/pages/include.jsp"%>

<form:form method="post" commandName="record" action="${pageContext.servletContext.contextPath}/company/updated">
	<div class="row">
		<div class="col-md-6">

			<div class="control-group">
				<form:hidden path="id"/>
				<form:hidden path="createdBy"/>
				<form:hidden path="createdDate"/>
				<form:hidden path="lastModifiedBy"/>
				<form:hidden path="lastModifiedDate"/>
				<form:input class="form-control" path="name" autocomplete="off"/>
			</div>

			<div class="clearfix"></div>
			
			<div class="well well-sm" style="margin-top: 25px; text-align: right;">
				<input type="submit" class="btn btn-sm btn-primary" value="Update"/>
				<button class="btn btn-sm btn-default"  type="button" onclick='window.location.href="${pageContext.servletContext.contextPath}/company/retrieve"'>Cancel</button>
			</div>

		</div>

	</div>
</form:form>