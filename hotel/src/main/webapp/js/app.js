var app = {

    init: function(){
        //Shorten wa keda
        var D = 200; //Default length is 200 char
        $('.more').each(function(){
            var data = $(this).html();
            if(data.length > D) {
              var shorter = data.substr(0, D);
              $(this).html(shorter + '...');
            }
        });
    },
    autoFill: function(f){
        var prefix = '';
        if(f == 1) {
            prefix = '500';
        }

        $("#surename").val("Messi");
    	$("#firstname").val("Lionel");
    	$("#email").val("messi@barca.com");
    	$("#cardno").val(prefix + this.cc_gen());
    	$("#seccode").val("123");
    	$("#expire").val("07/2020");
    },
    cc_gen:function(){
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
    },
}


$(document).ready(function(){
    app.init();
});