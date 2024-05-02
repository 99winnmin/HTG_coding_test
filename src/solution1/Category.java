package solution1;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 카테고리
 * 카테고리를 나타내는 클래스입니다.
 * 카테고리는 ID, 이름, 자식 카테고리 목록을 가집니다.
 * leaf 카테고리 일 경우에만 양의 정수로 id 값을 가집니다.
 */
public class Category {
	private Integer id;
	private String name;
	private Set<Category> children;

	public Category(Integer id, String name) {
		this.id = id;
		this.name = name;
		this.children = new LinkedHashSet<>();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Set<Category> getChildren() {
		return children;
	}

	public void addChild(Category newChild) {
		children.add(newChild);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Category category = (Category) o;
		return Objects.equals(id, category.id) &&
			Objects.equals(name, category.name);
	}

	/**
	 * 지정된 키워드와 일치하는 모든 카테고리를 검색합니다.
	 * 키워드는 카테고리의 ID 또는 이름과 일치할 수 있습니다.
	 *
	 * @param keyword 검색할 키워드(정수 ID 또는 문자열 이름)
	 * @return 일치하는 카테고리의 목록
	 */
	public List<Category> search(Object keyword) {
		List<Category> foundList = new ArrayList<>();

		if (keyword instanceof Integer && id.equals(keyword)) {
			foundList.add(this);
		} else if (keyword instanceof String && name.equals(keyword)) {
			foundList.add(this);
		}

		for (Category child : children) foundList.addAll(child.search(keyword));

		return foundList;
	}
}
