$(function(){

    //  DELETING CATEGORY
    $('.my-size-to-delete').on('click', function (e){
        e.preventDefault();

        var $size = $(this);
        var sizeId = $size.val();

        
        $.ajax({
           
            type: "DELETE",
            url: "/sizes/" + sizeId,
            
            data: {},
            
            success: function() {
                
                $('tr[data-size-id="' + sizeId + '"]').remove();
                
                $('#my-notice').addClass('my-notice-green').removeClass("my-no-line-height");
                $('#my-notice i').addClass('far fa-smile');
                $('#my-notice span:last-child').text("Size #" + sizeId + " has successfully been deleted.");
                    
            },
            
            
            error: function() {
            	
                $('#my-notice').addClass('my-notice-red').removeClass("my-no-line-height");
                $('#my-notice i').addClass('fas fa-dizzy');
                $('#my-notice span:last-child').text("Something went wrong with deletion of size #" + sizeId);
            
            }
            
        });
        
    });

});
