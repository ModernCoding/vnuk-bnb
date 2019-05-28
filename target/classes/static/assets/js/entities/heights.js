$(function(){

    //  DELETING TITLE
    $('.my-height-to-delete').on('click', function (e){
        e.preventDefault();

        var $height = $(this);
        var heightId = $height.val();

        
        $.ajax({
           
            type: "DELETE",
            url: "/heights/" + heightId,
            
            data: {},
            
            success: function() {
                
                $('tr[data-height-id="' + heightId + '"]').remove();
                
                $('#my-notice').addClass('my-notice-green').removeClass("my-no-line-height");
                $('#my-notice i').addClass('far fa-smile');
                $('#my-notice span:last-child').text("Height #" + heightId + " has successfully been deleted.");
                    
            },
            
            
            error: function() {
            	
                $('#my-notice').addClass('my-notice-red').removeClass("my-no-line-height");
                $('#my-notice i').addClass('fas fa-dizzy');
                $('#my-notice span:last-child').text("Something went wrong with deletion of height #" + heightId);
            
            }
            
        });
        
    });

});
