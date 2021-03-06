$('body').on('click', '.delete', function(){
  	var id = $(this).closest('tr').find("td:eq(0)").text();
  	var self = $(this);
	var yes = confirm("Вы действительно хотите удалить?");
	if(yes){
		$.ajax({
		url:'http://localhost:8080/productStorage/delete/'+id,
		type:'get',
		success: function(data) {
			self.closest('tr').remove();
			}
		});
	}
});

$('body').on('click', '.cancel', deleteForm);

function deleteForm(){
	var node = document.querySelector('#container');
	node.parentNode.removeChild(node);
}

function readData(){
    var data = {};

    $('form').find('input[type="text"], textarea').each(function(){
		data[this.name+''] = $(this).val(); 
    });
 
    if ($('input[type="checkbox"]').is(':checked')){
        data.reversed = 'true';
    } else {
        data.reversed = 'false';
    }

    return data;
}

function buildRow(obj){
	var time = obj.creationDate;
	var date = new Date(time);
	var dateString = date.getFullYear() + "-" + getZero(date.getMonth() + 1) + "-" + getZero(date.getDate());
	var res = '<tr><td>'+obj.id+'</td><td>'+obj.name+'</td><td>'+obj.description+'</td><td>'+dateString+'</td><td>'+obj.placeStorage+'</td><td>'+obj.reversed+'</td><td><input type="button" class="edit" value="Редактировать"><span> </span><input type="button" class="delete" value="Удалить"></td></tr>';
			
	return res;
}

$('body').on('click', '.create', function(){
    var str = '<div id="container"><form action=""><fieldset><legend>Добавить новый товар</legend><p>Введите название товара</p><input type="text" name="name"><p>Введите описание товара</p><textarea rows="5" cols="26" name="description"></textarea><p>Введите дату добавления товара</p><input type="text" name="creationDate" pattern="^[0-9]{4}\-[0-9]{2}\-[0-9]{2}$" placeholder="YYYY-MM-DD"><p>Введите номер ячейки хранения</p><input type="text" name="placeStorage" placeholder="Введите число" pattern="^[0-9]+$"></p><p>Резервирование товара <input type="checkbox" name="reversed"></p></fieldset><div class="buttons"><input type="button" class="crt" value="Отправить"><input type="button" class="cancel" value="Отменить"></div></form></div>';
    $('body').append(str);
});

$('body').on('click', '.crt', function(){
	$.ajax({
		url:'http://localhost:8080/productStorage/new',
		type:'post',
		contentType:'application/json',
		data: JSON.stringify(readData()),
		dataType:'json',
		success: function(result) {
			deleteForm();
			//$('table').append(buildRow(result));
			//$('table tr:last-child')
			$('tr:last').before(buildRow(result));
			//$(buildRow(result)).insertBefore($('tr:last'));
		}
	});
});

function getZero(num){
	if (num > 0 && num < 10) { 
		return '0' + num;
	} else {
		return num;
	}
}

var editTr;

$('body').on('click', '.edit', function(){
	var arr = [];
	editTr = $(this).closest('tr');
	$(this).closest('tr').find('td:not(:last)').each(function(index, elem) {
        	 arr.push($(elem).text());
    	});
	var flag = arr[1]?'checked':'';
	var str = '<div id="container"><form action=""><fieldset><legend>Редактировать товар</legend><p>Идентификатор товара</p><input type="text" name="id" value="'+arr[0]+'" disabled><p>Название товара</p><input type="text" value="'+arr[1]+'" name="name"><p>Описание товара</p><textarea rows="5" cols="26" name="description">'+arr[2]+'</textarea><p>Введите дату добавления товара</p><input type="text" value="'+arr[3]+'" name="creationDate" pattern="^[0-9]{4}\-[0-9]{2}\-[0-9]{2}$" placeholder="YYYY-MM-DD"><p>Номер ячейки хранения</p><input type="text" value="'+arr[4]+'" name="placeStorage" placeholder="Введите число" pattern="^[0-9]+$"></p><p><label>Резервирование товара <input type="checkbox" name="reversed" ' + flag + '></p></fieldset><div class="buttons"><input type="button" class="edt" value="Отправить"><input type="button" class="cancel" value="Отменить"></div></form></div>';
	$('body').append(str);
});

$('body').on('click', '.edt', function(){ 
	$.ajax({
		url:'http://localhost:8080/productStorage/edit',
		type:'post',
		contentType:'application/json',
		data: JSON.stringify(readData()),
		dataType:'json',
		success: function(result) {
			deleteForm();
			editTr.replaceWith(buildRow(result));
		}
	});
}); 