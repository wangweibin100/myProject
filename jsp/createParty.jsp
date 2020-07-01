<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
    #toggle-button1, #toggle-button2{ 
        display: none; 
    }
    .button-label{
        position: relative;
        display: inline-block;
        width: 80px;
        background-color: #ccc;
        border: 1px solid #ccc;
        border-radius: 30px;
        cursor: pointer;
    }
    .circle{
        position: absolute;
        top: 0;
        left: 0;
        width: 30px;
        height: 30px;
        border-radius: 50%;
        background-color: #fff;
    }
    .button-label .text {
        line-height: 30px;
        font-size: 18px;

        /*
        用来阻止页面文字被选中，出现蓝色
        可以将下面两行代码注释掉来查看区别
        */
       -webkit-user-select: none;
       user-select: none;
    }
    .on { 
        color: #fff; 
        display: none; 
        text-indent: 10px;
    }
    .off { 
        color: #fff; 
        display: inline-block; 
        text-indent: 53px;
    }
    .button-label .circle{
        left: 0;
        transition: all 0.3s;/*transition过度，时间为0.3秒*/
    }

    /*
    以下是checked被选中后，紧跟checked标签后面label的样式。
    例如：div+p 选择所有紧接着<div>元素之后的<p>元素
    */
    #toggle-button1:checked + label.button-label .circle{
        left: 50px;
    }
    #toggle-button1:checked + label.button-label .on{ 
        display: inline-block; 
    }
    #toggle-button1:checked + label.button-label .off{ 
        display: none; 
    }
    #toggle-button1:checked + label.button-label{
        background-color: #33FF66;
    }
    
    #toggle-button2:checked + label.button-label .circle{
        left: 50px;
    }
    #toggle-button2:checked + label.button-label .on{ 
        display: inline-block; 
    }
    #toggle-button2:checked + label.button-label .off{ 
        display: none; 
    }
    #toggle-button2:checked + label.button-label{
        background-color: #33FF66;
    }
</style>
</head>
<body>
<script type="text/javascript">
    $(document).ready(function(){
        /* $('#submitForm').click(function(){
            var formData = new Object;
            formData.username = $('#username').val();
            formData.password = $('#password').val();
            formData.name = $('#name').val();
            formData.email = $('#email').val();
            var JsonData = JSON.stringify(formData); 
            console.log(JsonData);
            $.ajax({
                type : "POST",
                url : "/Shop/JSON",
                dataType : 'text',  
                contentType: "application/json",
                data : JsonData,
                success : function(data) {
                   alert(data);
                }
            });
        });   */      
    });
</script>
<h1>预定会议室</h1>
<c:forEach items="${infoList}" var="str" varStatus="status">
<span>${str}</span><br></br>
</c:forEach>
<form action="/WebTestProject/CreateParty" method="post">
	<table style="width:50%">
		<tr style="height:20px;">
			<td style="width:25%;text-align:right;">会议室</td>
			<td style="width:20%">
				<select style="width:100%;" id="party_id" name="party_id">
					<c:forEach items="${partyRoomList}" var="item" varStatus="status">
						<option value="${item.itemCode}"> ${item.itemValue}</option>
					</c:forEach>
				</select>
			</td>
			<td style="width:25%"></td>
			<td style="width:25%"></td>
		</tr>
		<tr>
			<td rowspan="2" style="text-align:right;">预定时间</td>
			<td rowspan="2"><span>${partyDate}</span></td>
			<td style="left:20px;">周期预定
				<input type="checkbox" id="toggle-button1">
			    <!--label中的for跟input的id绑定。作用是在点击label时选中input或者取消选中input-->
			    <label for="toggle-button1" class="button-label">
			        <span class="circle"></span>
			        <span class="text on">开</span>
			        <span class="text off">关</span>
			    </label>
			</td>
			<td></td>
		</tr>
		<tr>
			<td style="left:20px;">连续预定
			    <input type="checkbox" id="toggle-button2">
			    <!--label中的for跟input的id绑定。作用是在点击label时选中input或者取消选中input-->
			    <label for="toggle-button2" class="button-label">
			        <span class="circle"></span>
			        <span class="text on">开</span>
			        <span class="text off">关</span>
			    </label>
			</td>
			<td>
			</td>
		</tr>
		<tr>
			<td style="text-align:right;">开始时间</td>
            <td>
            	<select style="width:100%;" id="start_time" name="start_time">
            		<c:forEach items="${timeList}" var="item" varStatus="status">
						<option value="${item.itemCode}"> ${item.itemValue}</option>
					</c:forEach>
				</select>
			</td>
            <td></td>
            <td></td>
		</tr>
		<tr>
			<td style="text-align:right;">结束时间</td>
            <td>
            	<select style="width:100%;" id="end_time" name="end_time">
					<c:forEach items="${timeList}" var="item" varStatus="status">
						<option value="${item.itemCode}"> ${item.itemValue}</option>
					</c:forEach>
				</select>
			</td>
            <td></td>
            <td></td>
		</tr>
		<tr>
			<td style="text-align:right;">预定人姓名</td>
			<td><input name="user_name" placeholder="请填写预定人姓名" id="user_name" style="width:118px;"></input></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td style="text-align:right;">预定人电话</td>
			<td><input name="tel_no" placeholder="请填写预定人电话" id="tel_no" style="width:118px;"></input></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td style="text-align:right;">邮箱</td>
			<td>
				<div style="font-size: 0;">
					<input name="email_no" id="email_no" style="width:102px;"></input>
					<input style="width:88px;" disabled value="@neusoft.com"></input>
				</div>
			</td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td style="text-align:right;">预定原因</td>
            <td>
            	<select style="width:100%;" id="resion_id" name="resion_id">
					<c:forEach items="${resionList}" var="item" varStatus="status">
						<option value="${item.itemCode}"> ${item.itemValue}</option>
					</c:forEach>
				</select>
			</td>
            <td></td>
            <td></td>
		</tr>
		<tr>
			<td style="text-align:right;">备注</td>
            <td>
            	<textarea name="comment" id="comment" rows="4" cols="22"></textarea>
			</td>
            <td></td>
            <td></td>
		</tr>
		<tr>
			<td style="text-align:right;">
				<button id="submit" name="submit" type="submit">提交</button>
			</td>
            <td>
			</td>
            <td></td>
            <td></td>
		</tr>
	</table>
	<input type="hidden" name="party_date" value="${partyDate}" id="party_date"></input>
</form>
</body>
</html>