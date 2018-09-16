/************************************
 * ANIME.JS BINDER
************************************/
function bind_post_Anime(tgt_list , tgt){
	$(tgt_list).find($(tgt)).each(function(){
		$(this).css("left","450px").css("top","450px")
	})
	tgt_list = "." + tgt_list
	tgt      = "." + tgt
	var post_anime = anime({
		targets: tgt_list+" "+tgt,
		translateX: -450,
		translateY: -450,
		direction: "alternative",
		loop: false,
		delay      : function(tgt, i , l){
			return i * 100;
		},
		duration   : function(tgt, i , l){
			return 250 + ( i * 250 )
		},
		elasticity : function(tgt, i , l){
			return (200+ i * 200)
		}
	})
	return post_anime
}
