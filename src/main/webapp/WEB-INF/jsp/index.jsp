<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="common/header.jsp"%>

<div class="container">
	<br>
	<div class="row">
		<!-- 
			<div class="col-xs-2">
				<div class="list-group">
            		<a href="#" class="list-group-item active">问题账单</a>
				</div>
			</div>
			 -->
		<div class="col-sm-12 bgcoloreeeeee">
			<div class="row" style="padding-top: 12px;">
				<div class="col-sm-12">
					<table class="table table-striped">
						<thead>
							<tr class="warning">
								<th>业务交易时间</th>
								<th>支付交易时间</th>
								<th>业务订单号</th>
								<th>支付订单号</th>
								<th>业务交易金额</th>
								<th>支付交易金额</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${ebs }" var="eb" varStatus="status">
							<tr class="${status.index % 2 eq 0 ? 'danger' : 'success'}">
								<td>${eb.serviceDealTime }</td>
								<td>${eb.payDealTime }</td>
								<td>${eb.serviceOrderNum }</td>
								<td>${eb.payOrderNum }</td>
								<td>${eb.seriveDealAmount }</td>
								<td>${eb.payDealAmount }</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row">
				<div class="foot-icpinfo col-sm-12">
					<a href="">首&nbsp;&nbsp;&nbsp;&nbsp;页</a>
					<a href="">下一页</a>
				</div>
			</div>
			<br>
		</div>
	</div>

	<%@ include file="common/footer.jsp"%>