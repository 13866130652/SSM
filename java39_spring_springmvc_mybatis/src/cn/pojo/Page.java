package cn.pojo;

import java.util.List;

import org.springframework.stereotype.Component;

@Component("page")
public class Page<T> {
	private Integer page;// 当前的页数
	private Integer totalCount;// 总条数
	private Integer rowNum;// 当前行数
	private static final Integer pageCount = 4;// 每页10条
	private Integer totalPage;// 总页数
	private List<T> list;

	public Integer getPageCount() {
		return pageCount;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	// 获得总页数
	public void setTotalPage() {
		this.totalPage = (totalCount % pageCount == 0) ? (totalCount / pageCount) : (totalCount / pageCount + 1);
		// select count(*) from news ;rt,getIn(1);
	}

	public Integer getPage() {
		if (page > totalPage) {
			page = totalPage;
		}
		if (page < 1) {
			page = 1;
		}
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getRowNum() {
		return rowNum;
	}

	// 获得当前的行号
	public void setRowNum() {
		if (page >= 1) {
			rowNum = (page - 1) * pageCount;
			if (rowNum > totalCount) {
				rowNum = (totalPage - 1) * pageCount;
				page = totalPage;
			}
		} else {// 考虑0和负数 条件
			page = 1;
			setRowNum();
		}
	}

	@Override
	public String toString() {
		return "Page [page=" + page + ", totalCount=" + totalCount + ", rowNum=" + rowNum + ", totalPage=" + totalPage
				+ "]";
	}

	/*
	 * 
	 * select * from news order by ncreatedate desc limit ?,?; rowNum,pageCount
	 * 
	 */

}
