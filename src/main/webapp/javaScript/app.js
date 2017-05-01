/*
 * Custom JavaScript for this app
 */

(function ($, window, document) {
    $(function () {

        // declare JQuery selectors and cache results
        var $btnAdd = $('#btnAdd');
        var $btnSearch = $('#btnSearch');
        var $btnDelete = $('#btnDelete');
        var $btnSave = $('#btnSave');
        var $authorId = $('#authorId');
        var $authorName = $('#name');
      
        var $searchKey = $('#searchKey');
        var baseUrl = "AuthorController";

        findAll();
        $btnDelete.hide();

        $btnAdd.on('click', function () {
            clearForm();
            $btnDelete.hide();
            $authorName.focus();
            return;
        });

        $btnSave.click(function () {
            if ($authorId.val() === '') {
                addAuthor();
            } else {
                updateAuthor();
            }
            return false;
        });

        $btnDelete.click(function () {
            deleteAuthor();
            return false;
        });

        function clearForm() {
            $aurhorId.val("");
            $authorName.val("");

        }

        function findAll() {
            $.get(baseUrl + "?action=list").then(function (authors) {
                renderList(authors);
            }, handleError);
        }

        function renderList(authors) {
            $('#authorList li').remove();
            $.each(authors, function (index, author) {
                $('#authorList').append('<li><a href="#" data-identity="'
                        + baseUrl + '?action=findone&authorId='
                        + author.authorId + '">' + author.name + '</a></li>');
            });
        }

        function handleError(xhr, status, error) {
            alert("Sorry, there was a problem: " + error);
        }

        $('#authorList').on('click', "a", function () {
            findById($(this).data('identity'));
        });

        function findById(self) {
            $.get(self).then(function (author) {
                renderDetails(author);
                $btnDelete.show();
            }, handleError);
            return false;
        }

        function renderDetails(author) {
            if (author.name === undefined) {
                $('#authorId').val(author.authorId);
            } else {
                var id = author.authorId;
                $('#authorId').val(id);
            }
            $('#name').val(author.name);
           
        }

        /*
         * The searchKey is any term that is part of an author name, 
         */
        $btnSearch.on('click', function () {
            var searchKey = $searchKey.val();
            searchKey = escapeHtml(searchKey.trim());
            var url = "AuthorController?action=search&searchKey=" + searchKey;
            $.get(url).then(function (author) {
                renderDetails(author);
                $btnDelete.show();
            }, handleError);
        });

        var htmlEscapeCodeMap = {
            "&": "&amp;",
            "<": "&lt;",
            ">": "&gt;",
            '"': '&quot;',
            "'": '&#39;',
            "/": '&#x2F;'
        };

        function escapeHtml(string) {
            return String(string).replace(/[&<>"'\/]/g, function (s) {
                return htmlEscapeCodeMap[s];
            });
        }

        var addAuthor = function () {
            $.ajax({
                type: 'POST',
                contentType: 'application/json',
                url: baseUrl + "?action=update",
                dataType: "json",
                data: formToJSON()
            })
            .done(function () {
                findAll();
                $btnDelete.show();
                alert("Author added successfully");
            })
            .fail(function ( jqXHR, textStatus, errorThrown ) {
                alert("Author could not be added due to: " + errorThrown);
            });
        }


        var updateAuthor = function () {
            console.log('updateauthor');
            $.ajax({
                type: 'POST',
                contentType: 'application/json',
                url: baseUrl + "?action=update",
                dataType: "json",
                data: formToJSON()
            })
            .done(function () {
                findAll();
                $btnDelete.show();
                alert("author updated successfully");
            })
            .fail(function ( jqXHR, textStatus, errorThrown ) {
                alert("author could not be updated due to: " + errorThrown);
            });
        }

        var deleteAuthor = function () {
            console.log('deleteauthor');
            $.ajax({
                type: 'POST',
                url: baseUrl + "?action=delete&authorId=" + $authorId.val()
            })
            .done(function () {
                findAll();
                clearForm();
                $btnDelete.hide();
                alert("author deleted successfully");
            })
            .fail(function ( jqXHR, textStatus, errorThrown ) {
                alert("author could not be deleted due to: " + errorThrown);
            });
        }

        function renderDetails(author) {
            if (author.name === undefined) {
                $('#authorId').val(author.authorId);
            } else {
                var id = author.authorId;
                $('#authorId').val(id);
            }
            $('#name').val(author.name);
         
        }

// Helper function to serialize all the form fields into a JSON string
        function formToJSON() {
            return JSON.stringify({
                "authorId": $authorId.val(),
                "name": $authorName.val(),
              
            });
        }
    });

}(window.jQuery, window, document));

