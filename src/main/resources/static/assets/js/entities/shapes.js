$(function(){

    //  DELETING SHAPE
    $('.my-shape-to-delete').on('click', function (e){
        e.preventDefault();

        var $shape = $(this);
        var shapeId = $shape.val();

        
        $.ajax({
           
            type: "DELETE",
            url: "/shapes/" + shapeId,
            
            data: {},
            
            success: function() {
                
                $('tr[data-shape-id="' + shapeId + '"]').remove();
                
                $('#my-notice').addClass('my-notice-green').removeClass("my-no-line-height");
                $('#my-notice i').addClass('far fa-smile');
                $('#my-notice span:last-child').text("Shape #" + shapeId + " has successfully been deleted.");
                    
            },
            
            
            error: function() {
            	
                $('#my-notice').addClass('my-notice-red').removeClass("my-no-line-height");
                $('#my-notice i').addClass('fas fa-dizzy');
                $('#my-notice span:last-child').text("Something went wrong with deletion of shape #" + shapeId);
            
            }
            
        });
        
    });

});
