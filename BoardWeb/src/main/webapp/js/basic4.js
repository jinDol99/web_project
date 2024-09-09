
//== 값 가져오기 & 가져온 값 출력하기 ==//

console.log(json);					// 문자열
let data = JSON.parse(json);		// 문자열 -> 객체 변환

console.log(data);		// 객체

console.clear();

// data에 들어있는 건 수(여기선 30번) 만큼 반복
// 객체를 forEach 돌린다면 기본적으로 3개의 파라미터로 특정 정보를 알 수 있음.

// 첫 번째 매개변수(item)는 i번째의 객체를 출력.
// 두 번째 매개변수(index)는 객체의 인덱스 번호를 출력.
// 세 번째 매개변수(ary)는 객체 전체의 원본을 출력함.

data.forEach(function (item, index, ary) {	
	if(item.salary > 8000 && item.gender == "Female") {
		console.log(item);
		console.log(index);
		console.log("-----------")
	}
});

// forEach를 사용한 방법으로는 변수에 결과를 담을 수 없음.
// 대안으로 filter()을 사용하면 변수에 결과 객체를 담아 유용하게 사용할 수 있음.
let filterResult = data.filter(function (item, index, ary) {	
	if(item.salary > 8000 && item.gender == "Female") {
		console.log(item);
		return true;
	}
});

console.log(filterResult);



// ========== [24.09.08 숙제 완성] ============= //
console.clear();
let searchBtn = document.querySelector("#searchBtn");

searchBtn.addEventListener("click", function() {
	console.clear();
	
	let salarySrch = document.getElementById("salary").value;
	let genderSrch = document.querySelector("#gender").value;
	console.log(salary + gender);
		
	let searchResult = data.filter(function(item, index, array) {
		if(item.salary > salarySrch && item.gender == genderSrch) {
			console.log(item);
			return true;
		}
	})
});






