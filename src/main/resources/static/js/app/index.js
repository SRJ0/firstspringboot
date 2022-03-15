var main = {
    init: function() {
        var _this = this;
        $('#btn-save').on('click', function() {
            _this.save();
        });
        $('#btn-update').on('click',function() {
            _this.update();
        });
    },

    save: function() {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },

    update: function() {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };
        var id = $('#id').val();

        $.ajax({
            type: 'PUT', // CRUD = post get put delete
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    }
}

main.init();
// 함수 이름이 중복될 경우 browser scope는 공용이므로, 덮어쓰게 된다.
// 이를 피하기 위해 객체 안에서만 function이 유효하도록 만든다.
