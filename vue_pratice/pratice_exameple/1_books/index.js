var app = new Vue({
	el: '#app1',
	data:{
		sortparam:'',
		book:{
			id: 0,
			author: '',
			name: '',
			price: ''
		},
		books:[{
			id: 1,
			author: 'aa',
			name: 'aaa',
			price: 32.0
		},{
			id: 2,
			author: 'bb',
			name: 'bbb',
			price: 23.0
		}],
		nextId: 4
	},
	computed:{
		sum:function(){
			var result = 0;
			for (var i=0; i < this.books.length; i++) {
				result += Number(this.books[i].price);
			}
			return result;
		}
	},
	methods:{
		addBook:function(){
			this.book.id = this.nextId;
			var bb = JSON.parse(JSON.stringify(this.book))
			this.books.push(bb);
			for(var i in this.book){
				if("number"===typeof(this.book[i])){
					this.nextId ++;
				} else {
					this.book[i] = '';
				}
				//this.book[i] = 
			}
		},
		delBook:function(book){
			//this.books.$remove(book);
			console.log(this.index);
			this.books.splice(this.index,1);
		},
		sortBy:function(sortparam){
			this.sortparam = sortparam;
		}
	}
});