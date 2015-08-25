<%@ include file="/WEB-INF/pages/include.jsp"%>

<form:form method="post" commandName="record" action="created">
	<div class="row">
		<div class="col-md-6">

			<div class="control-group">
				<input type="text" class="form-control" name="name" autocomplete="off" required="required" placeholder="Company name"/>
			</div>

			<div class="clearfix"></div>
			
			<div class="well well-sm" style="margin-top: 25px; text-align: right;">
				<input type="submit" class="btn btn-sm btn-primary" value="Create"/>
				<button class="btn btn-sm btn-default"  type="button" onclick='window.location.href="retrieve"'>Cancel</button>
			</div>

		</div>

	</div>
</form:form> 