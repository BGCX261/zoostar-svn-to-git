<%@ include file="/WEB-INF/pages/include.jsp"%>

<form:form action="create" method="get">
	<button type="submit" class="btn btn-primary">Create</button>

	<table class="table table-hover">
		<thead>
			<tr>
				<th>Name</th>
				<th>Created By</th>
				<th>Created Date</th>
				<th>Modified By</th>
				<th>Modified Date</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="record" items="${records}">
				<tr>
					<td>${record.name}</td>
					<td>${record.createdBy}</td>
					<td>${record.createdDate}</td>
					<td>${record.lastModifiedBy}</td>
					<td>${record.lastModifiedDate}</td>
					<td>
						<a class="btn btn-info btn-xs" href="update/${record.id}"><span class="glyphicon glyphicon-edit"></span></a>
						<a class="btn btn-danger btn-xs" href="delete/${record.id}" onclick="return confirm('Are you sure you want to delete?')"><span class="glyphicon glyphicon-remove"></span></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</form:form>
	