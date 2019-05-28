$(function(){

    //  DELETING COLLAR
    $('.my-collar-to-delete').on('click', function (e){
        e.preventDefault();

        var $collar = $(this);
        var collarId = $collar.val();

        
        $.ajax({
           
            type: "DELETE",
            url: "/collars/" + collarId,
            
            data: {},
            
            success: function() {
                
                $('tr[data-collar-id="' + collarId + '"]').remove();
                
                $('#my-notice').addClass('my-notice-green').removeClass("my-no-line-height");
                $('#my-notice i').addClass('far fa-smile');
                $('#my-notice span:last-child').text("Collar #" + collarId + " has successfully been deleted.");
                    
            },
            
            
            error: function() {
            	
                $('#my-notice').addClass('my-notice-red').removeClass("my-no-line-height");
                $('#my-notice i').addClass('fas fa-dizzy');
                $('#my-notice span:last-child').text("Something went wrong with deletion of collar #" + collarId);
            
            }
            
        });
        
    });

});
