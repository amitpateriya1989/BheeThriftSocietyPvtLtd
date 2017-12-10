<!-- ----------------Start Loading box----------------- -->
<div id="modelLoad" class="hide">
	<div class="loading1">
		<div class="text-center">
		<img src="${pageContext.request.contextPath}/assets/img/Preloader_8.gif" name="load-image" alt="load-image" />
		Loading........
		</div>
	</div><!--End loading1-->
</div> <!--End ModelLoad-->
<script type="text/javascript">
    $(function () {
        var loading = $('.loading1');
        if (loading[0] != null) {
              var top = Math.max($(window).height() / 2 - loading[0].offsetHeight / 2, 0);
              var left = Math.max($(window).width() / 2 - loading[0].offsetWidth / 2, 0);
              loading.css({ top: top, left: left });
        }
        
     $('#modelLoad .loading1').click(function(){
    	 $('#modelLoad').addClass('hide').removeClass('show');
     });   
        
    });
</script>
<script type="text/javascript">
$(document).ready(
		
  function() {
	//$("body").ajaxError(
	 // function(e,request) {
		//if (request.status == 403) {
			//window.location.reload();
		//window.location.href = "/myapp/login";
		//}
  	//});
	
	function loading_show(){
        $('#modelLoad').removeClass('hide').addClass('show');
     }
     function loading_hide(){
       $('#modelLoad').addClass('hide').removeClass('show');
     } 
     
     $( document ).ajaxStart(function() {
    	 loading_show();
     });
	
	$( document ).ajaxComplete(function() {
		loading_hide();
	});
	
	
});// end dom
</script>
<!-- -----------------------End loadning box--------------------- -->