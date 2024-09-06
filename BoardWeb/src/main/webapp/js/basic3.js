
//== 값 가져오기 & 가져온 값 출력하기 ==//

let addBtn = document.querySelector("#addBtn");

// 클릭이벤트 설정 및 발생 시 해야할 일
addBtn.addEventListener("click", function(e) {
	let tr = document.createElement("tr");
	let obj = {
		id: document.querySelector("#id").value,
		name: document.querySelector("#name").value,
		point: document.querySelector("#point").value
	}
	/*
	console.log(obj.id);
	
	let result;
	
	result += "<td>" + obj.id + "</td>";
	result += "<td>" + obj.name + "</td>";
	result += "<td>" + obj.point + "</td>";
	
	tr.appendChild("td" + obj.id);
	tr.appendChild("td" + obj.name);
	tr.appendChild("td" + obj.point);*/
	
	/*document.querySelector("list").innerHTML(tr);
	document.querySelector("list").innerHTML = "<tr>" + result + "</tr>";
	*/
	
	for(let prop in obj) {
		let td = document.createElement("td");
		td.innerHTML = obj[prop];
		tr.appendChild(td);
	}
	
	// tbody의 하위요소로 추가
	document.querySelector("#list").appendChild(tr);
	
	// 입력값 초기화
	document.querySelector("#id").value = "";
	document.querySelector("#name").value = "";
	document.querySelector("#point").value = "";
	
	
});
