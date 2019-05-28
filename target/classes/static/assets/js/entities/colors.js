$(function(){

    //  DELETING COLOR
    $('.my-color-to-delete').on('click', function (e){
        e.preventDefault();

        var $color = $(this);
        var colorId = $color.val();

        
        $.ajax({
           
            type: "DELETE",
            url: "/colors/" + colorId,
            
            data: {},
            
            success: function() {
                
                $('tr[data-color-id="' + colorId + '"]').remove();
                
                $('#my-notice').addClass('my-notice-green').removeClass("my-no-line-height");
                $('#my-notice i').addClass('far fa-smile');
                $('#my-notice span:last-child').text("Color #" + colorId + " has successfully been deleted.");
                    
            },
            
            
            error: function() {
            	
                $('#my-notice').addClass('my-notice-red').removeClass("my-no-line-height");
                $('#my-notice i').addClass('fas fa-dizzy');
                $('#my-notice span:last-child').text("Something went wrong with deletion of color #" + colorId);
            
            }
            
        });
        
    });

});
