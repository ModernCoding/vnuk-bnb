$(function(){

    //  DELETING CATEGORY
    $('.my-category-to-delete').on('click', function (e){
        e.preventDefault();

        var $category = $(this);
        var categoryId = $category.val();

        
        $.ajax({
           
            type: "DELETE",
            url: "/categories/" + categoryId,
            
            data: {},
            
            success: function() {
                
                $('tr[data-category-id="' + categoryId + '"]').remove();
                
                $('#my-notice').addClass('my-notice-green').removeClass("my-no-line-height");
                $('#my-notice i').addClass('far fa-smile');
                $('#my-notice span:last-child').text("Category #" + categoryId + " has successfully been deleted.");
                    
            },
            
            
            error: function() {
            	
                $('#my-notice').addClass('my-notice-red').removeClass("my-no-line-height");
                $('#my-notice i').addClass('fas fa-dizzy');
                $('#my-notice span:last-child').text("Something went wrong with deletion of category #" + categoryId);
            
            }
            
        });
        
    });

});
