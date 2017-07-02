//자바스크립트 공통 함수
//null check 함수
function gfn_isNull(str) 
{
	//String
	if (str == null) return true;
	if (str == "NaN") return true;
	if (new String(str).valueOf() == "undefined") return true; 
	
	//객체
    var chkStr = new String(str);
    if( chkStr.valueOf() == "undefined" ) return true;
    if (chkStr == null) return true;    
    if (chkStr.toString().length == 0 ) return true;   
    return false; 
}

//submit 기능 함수
function ComSubmit(opt_formId) 
{
	//전송된 인자가 null이면 commonForm으로..
	//전송된 인자가 있으면 jsp에서 직접 지정한 form id로 전송(예를 들어 frm 같은 거..)
	//받은 form id를 초기화, 이때 this는 객체 자신(ComSubmit)
	this.formId = gfn_isNull(opt_formId) == true ? "commonForm" : opt_formId;
	//전송할 url 초기화
	this.url = "";
	
	//전송된 formId가 commonForm이면..
	if(this.formId == "commonForm")
	{
		//form id="commonForm"인 것 중 인덱스가 [0]인 엘리먼트(요소) reset
		//전송시 form 안에 담을 값들을 초기화(받을준비!!!)
		$("#commonForm")[0].reset();
		$("#commonForm").empty();
	}
	
	//setUrl(url)을 통해 들어오는 url 값을 넣어줌
	//jsp에서 comSubmit 객체를 생성하여 setUrl을 지정한 적이 있다.
	this.setUrl = function setUrl(url)
	{
		this.url = url;
	};
	
	//addParam도 setUrl과 같은 형식의 로직(jsp에서 보내주는 key와 value)
	this.addParam = function addParam(key, value)
	{
		// ex)#commonForm or #frm,
		$("#"+this.formId).
		//받아온 key와 value를 input 태그에 mapping
		append($("<input type='hidden' name='"+key+"' id='"+key+"' value='"+value+"' >"));
		
		//ex) <input type="hidden" name="IDX" id="IDX" value="IDX">
	};
	
	//submit도 마찬가지
	this.submit = function submit()
	{
		//ex) #commonForm의 [0]의 엘리먼트를 frm 객체로 받음
		var frm = $("#"+this.formId)[0];
		//url -> <form action="url">
		frm.action = this.url;
		//전송방식 지정
		//method -> <form action="url" method="post">
		frm.method = "post";
		//전송
		frm.submit();
		
		//결국 아래 코드와 같아짐, 기존에 우리가 데이터를 전송하는 form 태그를 사용
		//ex) <form id="commonForm" action="/sample/boardList" method="post"> 
		//    <body>............</body>
		//    <form/>
		//    <input type="submit" value="전송하기"/>
	};
}

//페이징 ajax
var fv_ajaxCallback = ""; //콜백 함수 객체 초기화
function ComAjax(opt_formId)
{
	//여기서 this는 객체(ComAjax)
    this.url = "";     
    this.formId = gfn_isNull(opt_formId) == true ? "commonForm" : opt_formId;
    this.param = "";
     
    if(this.formId == "commonForm")
    {
    	//commonForm으로 전달될 element 초기화
        
    	 $("#commonForm")[0].reset();
  	     $("#commonForm").empty();
  	     
    /*	var frm = $("#commonForm");
        if(frm.length > 0)
        {
            frm.remove();
        }
        var str = "<form id='commonForm' name='commonForm'></form>";
        $('body').append(str);*/
    }
    
    //url 세팅 
    this.setUrl = function setUrl(url)
    {
        this.url = url;
    };
     
    //추가
    //ajax를 이용하여 데이터를 전송한 후 호출될 콜백함수의 이름 지정
    //ajax는 비동기적으로 수행되기 때문에 return을 받을 수 없다.
    //그래서 서버에 요청 후 다시 클라이언트에 전달될 때 호출되는 함수가 콜백함수
    this.setCallback = function setCallback(callBack)
    {
        fv_ajaxCallback = callBack;
    };
    
    //전달될 param 값 세팅
    this.addParam = function addParam(key,value)
    {
        this.param = this.param + "&" + key + "=" + value;
    };
    
   //submit 대신에 ajax 함수
    this.ajax = function ajax()
    {
    	//form id가 commonForm이 아닌 경우
        if(this.formId != "commonForm")
        {
        	//param 세팅 
 		    //serialize 함수를 이용하면 모든 element를 일일이 지정해서 보내주지 않아도 된다.
            this.param += "&" + $("#" + this.formId).serialize();
        }
        
        $.ajax
        ({
        	//jason 표기
        	url : this.url,   
            type : "POST",  
            data : this.param,
            async : false,
            success : function(data, status) 
            {
            	//객체의 타입이 function일 경우
                if(typeof(fv_ajaxCallback) == "function")
                {
                	//data 파라미터를 가지는 fv_ajaxCallback 함수 호출
                    fv_ajaxCallback(data);
                }
                //function이 아닐 경우
                else 
                {
                	//eval 함수 : 문자열로 넘어온 javascript 구문을 실행
                    eval(fv_ajaxCallback + "(data);");
                }
            } //end of success
        }); //end of ajax
    }; //end of function
}


/*
divId : 페이징 태그가 그려질 div
pageIndx : 현재 페이지 위치가 저장될 input 태그 id
recordCount : 페이지당 레코드 수
totalCount : 전체 조회 건수
eventName : 페이징 하단의 숫자 등의 버튼이 클릭되었을 때 호출될 함수 이름
*/

var gfv_pageIndex = null;
var gfv_eventName = null;

//페이징 함수
function gfn_renderPaging(params)
{
    var divId = params.divId; //페이징이 그려질 div id
    gfv_pageIndex = params.pageIndex; //현재 위치가 저장될 input 태그
    var totalCount = params.totalCount; //전체 조회 건수
    
    //현재 위치
    //params.pageIndex == "PAGE_INDEX"
    //결국, #PAGE_INDEX(id가 PAGE_INDEX)의 value
    var currentIndex = $("#"+params.pageIndex).val(); 
    
    //PAGE_INDEX의 값의 길이가 0이거나 현재 페이지가 null이면..
    if($("#"+params.pageIndex).length == 0 || gfn_isNull(currentIndex) == true)
    {
        currentIndex = 1;
    }
     
    var recordCount = params.recordCount; //페이지당 게시물 수 
    
    //페이지당 게시물 수 가 null 이면..
    if(gfn_isNull(recordCount) == true)
    {
        recordCount = 15; //페이지당 게시물 수 지정
    }
    
    // ex) 500 / 20 = 25 => totalIndexCount 
    var totalIndexCount = Math.ceil(totalCount / recordCount); // 전체 인덱스 수
    gfv_eventName = params.eventName; //boardList.jsp에서 fn_selectBoardList
    
    //div태그 id 세팅
    $("#"+divId).empty();
    var preStr = "";
    var postStr = "";
    var str = "";
     
    var first = (parseInt((currentIndex-1) / 10) * 10) + 1; //맨 처음
    var last = (parseInt(totalIndexCount/10) == parseInt(currentIndex/10)) ? totalIndexCount%10 : 10; //맨 마지막
    var prev = (parseInt((currentIndex-1)/10)*10) - 9 > 0 ? (parseInt((currentIndex-1)/10)*10) - 9 : 1; // 이전
    // 다음
    var next = (parseInt((currentIndex-1)/10)+1) * 10 + 1 < totalIndexCount ? (parseInt((currentIndex-1)/10)+1) * 10 + 1 : totalIndexCount;
     
    if(totalIndexCount > 10) //전체 인덱스가 10이 넘을 경우, 맨앞, 앞 태그 작성(preStr)
    { 
        preStr += "<a href='#this' class='pad_5' onclick='_movePage(1)'>[<<]</a>" +
                "<a href='#this' class='pad_5' onclick='_movePage("+prev+")'>[<]</a>";
    }
    else if(totalIndexCount <=10 && totalIndexCount > 1) //전체 인덱스가 10보다 작을경우, 맨앞 태그 작성
    { 
        preStr += "<a href='#this' class='pad_5' onclick='_movePage(1)'>[<<]</a>";
    }
     
    if(totalIndexCount > 10) //전체 인덱스가 10이 넘을 경우, 맨뒤, 뒤 태그 작성
    { 
        postStr += "<a href='#this' class='pad_5' onclick='_movePage("+next+")'>[>]</a>" +
                    "<a href='#this' class='pad_5' onclick='_movePage("+totalIndexCount+")'>[>>]</a>";
    }
    else if(totalIndexCount <=10 && totalIndexCount > 1) //전체 인덱스가 10보다 작을경우, 맨뒤 태그 작성
    { 
        postStr += "<a href='#this' class='pad_5' onclick='_movePage("+totalIndexCount+")'>[>>]</a>";
    }
     
    for(var i=first; i<(first+last); i++)
    {
        if(i != currentIndex)
        {
            str += "<a href='#this' class='pad_5' onclick='_movePage("+i+")'>"+i+"</a>";
        }
        else
        {
            str += "<b><a href='#this' class='pad_5' onclick='_movePage("+i+")'>"+i+"</a></b>";
        }
    }
    //div id == PAGE_NAVI 인것에 추가..
    $("#"+divId).append(preStr + str + postStr);
}

//페이지 이동 시 호출되는 메서드
function _movePage(value)
{
	//input id가 PAGE_INDEX인 것의 값(value는 페이징 처리 시 넘어오는 값)
    $("#"+gfv_pageIndex).val(value);
    if(typeof(gfv_eventName) == "function")
    {
        gfv_eventName(value);
    }
    else 
    {
        eval(gfv_eventName + "(value);");
    }
}


