jQuery(document).ready(function($) {
 
    $(".scroll").click(function(event){   
    event.preventDefault();
    $('html,body').animate({scrollTop:$(this.hash).offset().top}, 800,'swing');
    });
    });





// uniform
    $(function () {
    $('input[type="checkbox"], input[type="radio"], select').uniform();
    });

// social icon
$(document).ready(function($) {
  $('.social i').tooltip('hide')
});

// 

var wow = new WOW(
  {
    boxClass:     'wowload',      // animated element css class (default is wow)
    animateClass: 'animated', // animation css class (default is animated)
    offset:       0,          // distance to the element when triggering the animation (default is 0)
    mobile:       true,       // trigger animations on mobile devices (default is true)
    live:         true        // act on asynchronously loaded content (default is true)
  }
);
wow.init();




$('.carousel').swipe( {
     swipeLeft: function() {
         $(this).carousel('next');
     },
     swipeRight: function() {
         $(this).carousel('prev');
     },
     allowPageScroll: 'vertical'
 });



function autoFill(){
	$("#surename").val("Messi");
	$("#firstname").val("Lionel");
	$("#email").val("messi@barca.com");
	$("#cardno").val(cc_gen());
	$("#seccode").val("123");
	$("#expire").val("07/2020");
}

function autoFill500(){
	$("#surename").val("Messi");
	$("#firstname").val("Lionel");
	$("#email").val("messi@barca.com");
	$("#cardno").val("500" + cc_gen());
	$("#seccode").val("123");
	$("#expire").val("07/2020");
}


function cc_gen(){
	var pos = 1;
	var str = new Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
	var sum = 0;
	var final_digit = 0;
	var t = 0;
	var len_offset = 0;
	var len = 13;
	var issuer;
	str[0] = 4;
	
	while (pos < len - 1) {
		str[pos++] = Math.floor(Math.random() * 10) % 10;
	}
	
	len_offset = (len + 1) % 2;
	for (pos = 0; pos < len - 1; pos++) {
		if ((pos + len_offset) % 2) {
			t = str[pos] * 2;
			if (t > 9) {
				t -= 9;
			}
			sum += t;
		}
		else {
			sum += str[pos];
		}
	}
	
	final_digit = (10 - (sum % 10)) % 10;
	str[len - 1] = final_digit;
 
	t = str.join('');
	t = t.substr(0, len);
	
	return t;
}