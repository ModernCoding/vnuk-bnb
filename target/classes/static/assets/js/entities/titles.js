$(function(){

    //  DELETING TITLE
    $('.my-title-to-delete').on('click', function (e){
        e.preventDefault();

        var $title = $(this);
        var titleId = $title.val();

        
        $.ajax({
           
            type: "DELETE",
            url: "/titles/" + titleId,
            
            data: {},
            
            success: function() {
                
                $('tr[data-title-id="' + titleId + '"]').remove();
                
                $('#my-notice').addClass('my-notice-green').removeClass("my-no-line-height");
                $('#my-notice i').addClass('far fa-smile');
                $('#my-notice span:last-child').text("Title #" + titleId + " has successfully been deleted.");
                    
            },
            
            
            error: function() {
            	
                $('#my-notice').addClass('my-notice-red').removeClass("my-no-line-height");
                $('#my-notice i').addClass('fas fa-dizzy');
                $('#my-notice span:last-child').text("Something went wrong with deletion of title #" + titleId);
            
            }
            
        });
        
    });

});
