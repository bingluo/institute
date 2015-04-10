		$(document).ready(function(){
			
			var ue = UE.getEditor('editor');
			$("#confirm").click(function(){
				var title = $('input[id=title]').val();
				var content = ue.getContent();
				var data = 'title=' + title + '&content=' + encodeURIComponent(content);
				$.ajax({
					url: contextPath+"/admin/postArticle",	
					type: "POST",
					data: data,		
					cache: false,
					success: function (html) {				
						if (html==1) {	
							alert("发布成功！");
							location.href=contextPath+"/";
						}
						else {
							alert("发布失败。");			
						}
					}		
				});
			});
			
			$("#upBtn").click(function(){
				$("#fileForm").submit();
			});
			
			var $tabsNav    = $('.tabs-nav'),
				$tabsNavLis = $tabsNav.children('li'),
				$tabContent = $('.tab-content');
	
			$tabsNav.each(function() {
				var $this = $(this);
	
				$this.next().children('.tab-content').stop(true,true).hide()
													 .first().show();
	
				$this.children('li').first().addClass('active').stop(true,true).show();
			});
	
			$tabsNavLis.on('click', function(e) {
				var $this = $(this);
	
				$this.siblings().removeClass('active').end()
					 .addClass('active');
				
				$this.parent().next().children('.tab-content').stop(true,true).hide()
															  .siblings( $this.find('a').attr('href') ).fadeIn();
	
				e.preventDefault();
			});
		});