<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
function search() {
	var partyId = $("#partyId").val();
	var condition = {'partyId' : partyId};
	var data = JSON.stringify(condition);

	$.ajax({ 
	    type:"POST", 
	    url:"/SearchParty", 
	    dataType:"json",       
	    data:data, //这里不能进行JSON.stringify,发送请求的数据在:form data
	    success:function(data){ 
	    	$("#partyList tbody").html("");
	    	var tdStr = "";
	        for (var i = 0; i < data.length; i++) {
	        	tdStr = tdStr.append("<tr><td>" + data[i].partyId + "</td><td>" +
	        			data[i].partyDate +  "</td><td>" +
	        			data[i].time01 + "</td><td>" + 
	        			data[i].time02 + "</td><td>" + 
	        			data[i].time03 + "</td><td>" + 
	        			data[i].time04 + "</td><td>" + 
	        			data[i].time05 + "</td><td>" + 
	        			data[i].time06 + "</td><td>" + 
	        			data[i].time07 + "</td><td>" + 
	        			data[i].time08 + "</td><td>" + 
	        			data[i].time09 + "</td><td>" + 
	        			data[i].time10 + "</td><td>" + 
	        			data[i].time11 + "</td><td>" + 
	        			data[i].time12 + "</td><td>" + 
	        			data[i].time13 + "</td><td>" + 
	        			data[i].time14 + "</td><td>" + 
	        			data[i].time15 + "</td><td>" + 
	        			data[i].time16 + "</td><td>" + 
	        			data[i].time17 + "</td><td>" + 
	        			data[i].time18 + "</td></tr>");
	        }
	        $("#partyList tbody").append(tdStr);
	    }
	 });
}
 
</script>
<h1>会议室一览</h1>
	<table style="width:50%">
		<tr style="height:20px;">
			<td style="width:25%; text-align:right;">会议室</td>
			<td style="width:25%;">
				<select style="width:100%;" id="partyId" name="partyId">
					<c:forEach items="${partyRoomList}" var="item" varStatus="status">
						<option value="${item.itemCode}"> ${item.itemValue}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td style="text-align:right;">
				<button id="searchBtn" name="searchBtn" onclick="javascript:search();" type="button">查询</button>
			</td>
            <td>
			</td>
		</tr>
	</table>
	<table id="partyList">
		<thead>
			<tr>
				<th>会议室</th>
				<th>会议日期</th>
				<th>09:00</th>
				<th>09:30</th>
				<th>10:00</th>
				<th>10:30</th>
				<th>11:00</th>
				<th>11:30</th>
				<th>12:00</th>
				<th>12:30</th>
				<th>13:00</th>
				<th>13:30</th>
				<th>14:00</th>
				<th>14:30</th>
				<th>15:00</th>
				<th>15:30</th>
				<th>16:00</th>
				<th>16:30</th>
				<th>17:00</th>
				<th>17:30~18:00</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</body>
</html>