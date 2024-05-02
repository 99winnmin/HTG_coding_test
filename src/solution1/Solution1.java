package solution1;

import java.util.List;

public class Solution1 {

	public static void main(String[] args) {
		String json = "";
		Object searchWord = "엑소";
		Category root = init();

		List<Category> search = root.search(searchWord);

		if (search.size() == 1) json = JsonConverter.toJson(search.get(0), new StringBuilder(), 0);
		else json = JsonConverter.toJson(search, new StringBuilder(), 0);

		System.out.println(json);
		// 출력 예시
		// {
		//   "id": 0,
		//   "name": "엑소",
		//   "children": [
		//     {
		//       "id": 1,
		//       "name": "공지사항",
		//       "children": []
		//     },
		//     {
		//       "id": 2,
		//       "name": "첸",
		//       "children": []
		//     },
		//     {
		//       "id": 3,
		//       "name": "백현",
		//       "children": []
		//     },
		//     {
		//       "id": 4,
		//       "name": "시우민",
		//       "children": []
		//     }
		//   ]
		// }
	}

	public static Category init() {
		Category root = new Category(0,  "root");
		Category man = new Category(0, "남자");
		Category girl = new Category(0, "여자");
		Category exo = new Category(0, "엑소");
		Category bts = new Category(0, "방탄소년단");
		Category blackpink = new Category(0, "블랙핑크");
		Category announcement1 = new Category(1, "공지사항");
		Category chen = new Category(2, "첸");
		Category baekHyeon = new Category(3, "백현");
		Category xiumin = new Category(4, "시우민");
		Category announcement5 = new Category(5, "공지사항");
		Category anonymous = new Category(6, "익명게시판");
		Category v = new Category(7, "뷔");
		Category announcement8 = new Category(8, "공지사항");
		Category rose = new Category(9, "로제");

		root.addChild(man);
		root.addChild(girl);

		man.addChild(exo);
		man.addChild(bts);

		exo.addChild(announcement1);
		exo.addChild(chen);
		exo.addChild(baekHyeon);
		exo.addChild(xiumin);

		bts.addChild(announcement5);
		bts.addChild(anonymous);
		bts.addChild(v);

		girl.addChild(blackpink);
		blackpink.addChild(announcement8);
		blackpink.addChild(anonymous);
		blackpink.addChild(rose);
		return root;
	}

}
