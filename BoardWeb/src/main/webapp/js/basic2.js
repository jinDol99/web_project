
//== 즐거운 자바스크립트 시간 끼얏호우! ==//

let students = [
	{sno: 111, sname: '김민지', score :90},
	{sno: 112, sname: '홍길동', score :80},
	{sno: 113, sname: '박문수', score :70}
]


let table = document.createElement('table');
table.setAttribute("border", "2");					// 특정 태그(table)에 속성(border)값(2) 지정

// for문은 기본 증감식을 쓰는 반복문도 많이 쓰지만, 아래와 같이 for-of, for-in 반복문도 많이 쓰임
for (let student of students) {
	let tr = document.createElement("tr");
	
	for(let prop in student) {	
		let td = document.createElement("td");
		td.innerHTML = student[prop];		
		tr.appendChild(td);							// 하위요소를 상위요소에 부착
		console.log("속성: " + prop + " | 값: " + student[prop]);
	}
	table.appendChild(tr);
	console.log("==============================================")
}
document.querySelector("#show").appendChild(table);