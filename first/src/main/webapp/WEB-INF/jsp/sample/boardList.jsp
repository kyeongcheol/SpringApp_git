<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
</head>
<body>
    <h2>게시판 목록</h2>
    <table class="board_list">
        <colgroup>
            <col width="10%"/>
            <col width="*"/>
            <col width="15%"/>
            <col width="20%"/>
        </colgroup>
        <thead>
            <tr>
                <th scope="col">글번호</th>
                <th scope="col">제목</th>
                <th scope="col">조회수</th>
                <th scope="col">작성일</th>
            </tr>
        </thead>
        <tbody>
        <%--  <c:choose> --%>
  <%-- list의 길이가 0 이상이면 --%>
<%--    <c:when test="${fn:length(list) > 0}">
    <c:forEach items="${list}" var="row">
     <tr>
      <td>${row.IDX}</td>
      <td class="title">
        <a href="#this" name="title">${row.TITLE}</a>
        <input type="hidden" id="IDX" value="${row.IDX}">
      </td>
      <td>${row.HIT_CNT}</td>
      <td>${row.CREA_DTM}</td>
    </c:forEach>
   </c:when> --%>
   
   <%--list의 길이가 0 이하이면 --%>
  <%--  <c:otherwise>
    <tr>
      <td colspan="4">조회된 결과가 없습니다.</td>
    </tr>
    </c:otherwise>
   </c:choose>  --%>     
        </tbody>
    </table>
     
    <div id="PAGE_NAVI"></div>
    <input type="hidden" id="PAGE_INDEX" name="PAGE_INDEX"/>
     
    <br/>
    <a href="#this" class="btn" id="write">글쓰기</a>
    
    <%--생성한 form 불러오기 --%> 
    <%@ include file="/WEB-INF/include/include-body.jspf" %>
    
    <script type="text/javascript">
    //document : html 문서
    //ready : html 문서를 다 읽을때까지.. 로딩
        $(document).ready(function()
        {
            fn_selectBoardList(1); //(1) : pageNo
            
            //id가 write인 것을 click하면..(이벤트)
            $("#write").on("click", function(e) //글쓰기 버튼
            { 
                e.preventDefault(); //이벤트 전파 방지
                fn_openBoardWrite(); //메서드 호출
            });
            
          //상세보기 페이지
            $("a[name='title']").on("click", function(e) //제목
            { 
                e.preventDefault(); //이벤트 전파 방지
                //this : jQuery 객체 의미, a 태그의 name이 title
                fn_openBoardDetail($(this));
            });
        });
         
         
        function fn_openBoardWrite()
        {
        	//comSubmit : common.js에 있는 객체 생성
            var comSubmit = new ComSubmit();
            //호출하고 싶은 주소 지정
            comSubmit.setUrl("<c:url value='/sample/openBoardWrite.do' />");
            //c(jstl) 태그 사용 하지 않을 경우
            //comSubmit.setUrl("/first/sample/openBoardList.do");
            
            //comSubmit 객체 submit(전송)
            comSubmit.submit();
        }
         
        function fn_openBoardDetail(obj)
        {
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='/sample/openBoardDetail.do' />");
            
            //parameter로 IDX(글번호) 값을 넘겨줌
       	    //obj.parent(인자값의 부모 태그)
       	    //결국 obj는 a태그의 name이 'title'인 경우를 의미하므로..
       	    //obj.parent는 td 태그가 되고 그 안에 있는 id="IDX"를 찾는다.
       	 
       	    //addParam은 서버로 전송될 키와 값을 파라미터로 갖는다.
       	    //결국 controller에서 map에 키 :"IDX" , 값 : ${row.IDX}으로 들어감
            comSubmit.addParam("IDX", obj.parent().find("#IDX").val());
            comSubmit.submit();
        }
        
       //페이징 추가된 selectBoardList
        function fn_selectBoardList(pageNo)
        {
            var comAjax = new ComAjax(); //comAjax 객체 생성
            comAjax.setUrl("<c:url value='/sample/selectBoardList.do' />");
            comAjax.setCallback("fn_selectBoardListCallback");
            comAjax.addParam("PAGE_INDEX",pageNo);
            comAjax.addParam("PAGE_ROW", 15);
            comAjax.ajax();
        }
        
        //페이징 콜백 함수 
        function fn_selectBoardListCallback(data)
        {
            var total = data.TOTAL; //controller에서 보내준 data
            var body = $("table > tbody"); 
            body.empty();
            
            if(total == 0) //총 게시물 개수가 0이면..
            {
                var str = "<tr>" +
                                "<td colspan='4'>조회된 결과가 없습니다.</td>" +
                            "</tr>";
                body.append(str);
            }
            else
            {
                var params = 
                {
                    divId : "PAGE_NAVI",
                    pageIndex : "PAGE_INDEX",
                    totalCount : total,
                    eventName : "fn_selectBoardList"
                };
                
                //페이징 rendering 함수 호출
                gfn_renderPaging(params);
                 
                var str = "";
                //리스트 안에 있는 key와 value를 꺼내옴
                //실질적으로 게시물이 나오는 태그들..
                $.each(data.list, function(key, value)
                {
                    str += "<tr>" +
                                "<td>" + value.IDX + "</td>" +
                                "<td class='title'>" +
                                    "<a href='#this' name='title'>" + value.TITLE + "</a>" +
                                    "<input type='hidden' name='title' value=" + value.IDX + ">" +
                                "</td>" +
                                "<td>" + value.HIT_CNT + "</td>" +
                                "<td>" + value.CREA_DTM + "</td>" +
                            "</tr>";
                });
                body.append(str);
                
                //상세보기
                $("a[name='title']").on("click", function(e) //제목
                { 
                    e.preventDefault();
                    fn_openBoardDetail($(this));
                });
            }
        }
    </script>
</body>
</html>


