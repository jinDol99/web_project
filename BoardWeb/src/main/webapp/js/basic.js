
//== JS 간단 복습 ==//


console.log("basic.js");

let myName = "Hong";
let myAge = 20;

// JS에서도 아래와 같이 객체를 만들 수 있음.
const member = {
	id: 'user01',
	pw: '1111',
	email: 'user01@email.com',
	hobbies: ['sleeping', 'eating'],
	friends: [
		{name: 'Kim', phone: '1234-1234'},
		{name: 'Lee', phone: '3456-3456'},
		{name: 'Park', phone: '4567-4567'}
	],
	showHobby: function() {
		let result;
		this.hobbies.forEach(item => {
			result += item + " ";
		})
		return result;
	}
}

console.log(member.id);							// user01
console.log(member['pw']);						// 1111

let mailAddress = 'email';
console.log(member[mailAddress]);				// user@email.com

console.log(member.hobbies[1]);					// eating
console.log(member['friends'][0]['name']);		// kim

//member.phone = "010-1111-2222"
console.log(member);							// 010-1111-2222



//== DOM 연습 ==//

let tag = document.createElement('p');
tag.innerText = '이름: ' + myName;
document.querySelector('#show').appendChild(tag);

tag = document.createElement('p');
tag.innerHTML = '취미: <b>' + member.showHobby() + "</b>";
document.querySelector("#show").appendChild(tag);


// ul > li 2개로 출력하기
/*let li = document.createElement('ul');
for (i = 0; i < member.friends.length; i++) {
	li.innerHTML += "<li>"
	li.innerHTML += member.showFriends(i);
	console.log(member.showFriends(i));
	li.innerHTML += "</li>"
}
document.querySelector("#show").appendChild(tag);*/

tag = document.createElement('ul');

member.friends.forEach(friend => {
	let li = document.createElement('li');
	li.innerHTML = "이름 " + friend.name + ", 연락처 " +  friend.phone;
	
	let btn = document.createElement('button');
	btn.innerHTML = '삭제';
	btn.addEventListener('click', function() {li.remove();});
	
	li.appendChild(btn);	// li 태그의 하위요소 btn 붙이기
	tag.appendChild(li);	// ul 태그의 하위요소 li 붙이기
})
document.querySelector("#show").appendChild(tag);







