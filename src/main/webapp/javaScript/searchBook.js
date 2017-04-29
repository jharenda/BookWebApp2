/*
 * Custom JavaScript for searching 
 */

(function ($, window, document) {
    $(function () {

        // declare JQuery selectors and cache results
        var $btnAdd = $('#btnAdd');
        var $btnSearch = $('#btnSearch');
        var $btnDelete = $('#btnDelete');
        var $btnSave = $('#btnSave');
        var $bookId = $('#bookId');
        var $bookName = $('#bookName');
        var $bookAuthor = $('#bookAuthor');
        var $bookIsbn = $('#bookIsbn');
        var $bookAuthor = $('#bookAuthor');
        var $searchKey = $('#searchKey');
        var baseUrl = "BookController";

        findAll();
        $btnDelete.hide();

        $btnAdd.on('click', function () {
            clearForm();
            $btnDelete.hide();
            $bookName.focus();
            return;
        });

        $btnSave.click(function () {
            if ($bookId.val() === '') {
                addBook();
            } else {
                updateBook();
            }
            return false;
        });

        $btnDelete.click(function () {
            deleteBook();
            return false;
        });

        function clearForm() {
            $bookId.val("");
            $bookName.val("");
            $bookAuthor.val("");
            $bookIsbn.val("");
            
        }

        function findAll() {
            $.get(baseUrl + "?action=list").then(function (books) {
                renderList(books);
            }, handleError);
        }

        function renderList(books) {
            $('#bookList li').remove();
            $.each(books, function (index, book) {
                $('#bookList').append('<li><a href="#" data-identity="'
                        + baseUrl + '?action=findone&bookId='
                        + book.bookId + '">' + book.name + '</a></li>');
            });
        }

        function handleError(xhr, status, error) {
            alert("Sorry, there was a problem: " + error);
        }

        $('#bookList').on('click', "a", function () {
            findById($(this).data('identity'));
        });

        function findById(self) {
            $.get(self).then(function (book) {
                renderDetails(book);
                $btnDelete.show();
            }, handleError);
            return false;
        }

        function renderDetails(book) {
            if (book.name === undefined) {
                $('#bookId').val(book.bookId);
            } else {
                var id = book.bookId;
                $('#bookId').val(id);
            }
            $('#bookName').val(book.name);
           // $('#bookAuthor').val(book.author);
            $('#bookIsbn').val(book.isbn);
            //$('#zip').val(hotel.zip);
        }

        /*
         * The searchKey is any term that is part of a book name, isbn 
         * or ........
         */
        $btnSearch.on('click', function () {
            var searchKey = $searchKey.val();
            searchKey = escapeHtml(searchKey.trim());
            var url = "BookController?action=search&searchKey=" + searchKey;
            $.get(url).then(function (book) {
                renderDetails(book);
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

        var addBook = function () {
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
                alert("Book added successfully");
            })
            .fail(function ( jqXHR, textStatus, errorThrown ) {
                alert("Book could not be added due to: " + errorThrown);
            });
        }


        var updateBook = function () {
            console.log('updateHotel');
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
                alert("Hotel updated successfully");
            })
            .fail(function ( jqXHR, textStatus, errorThrown ) {
                alert("Hotel could not be updated due to: " + errorThrown);
            });
        }

        var deleteBook = function () {
            console.log('deleteBook');
            $.ajax({
                type: 'POST',
                url: baseUrl + "?action=delete&bookId=" + $bookId.val()
            })
            .done(function () {
                findAll();
                clearForm();
                $btnDelete.hide();
                alert("Book deleted successfully");
            })
            .fail(function ( jqXHR, textStatus, errorThrown ) {
                alert("Book could not be deleted due to: " + errorThrown);
            });
        }

       function renderDetails(book) {
            if (book.name === undefined) {
                $('#bookId').val(book.bookId);
            } else {
                var id = book.bookId;
                $('#bookId').val(id);
            }
            $('#bookName').val(book.name);
           // $('#bookAuthor').val(book.author);
            $('#bookIsbn').val(book.isbn);
           
        }


// Helper function to serialize all the form fields into a JSON string
        function formToJSON() {
            return JSON.stringify({
                "bookId": $bookId.val(),
                "bookName": $bookName.val(),
                "bookIsbn": $bookIsbn.val(),
               // "bookAutnor": $bookAuthor.val(),
                
            });
        }
    });

}(window.jQuery, window, document));

