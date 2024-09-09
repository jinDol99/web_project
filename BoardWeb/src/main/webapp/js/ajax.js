// == Ajax.js == //
// Asynchronous Javascript and Xml

// 동기 방식 처리
setTimeout(function() {
	console.log("a");
}, 1000)
console.log('b');
console.log('c');


// 비동기 방식 처리
setTimeout(function() {
	console.log("가");
}, 1000)
setTimeout(function() {
	console.log("나");
}, 1000)
setTimeout(function() {
	console.log("다");
}, 1000)

// ...뭐 어쩌라는거지