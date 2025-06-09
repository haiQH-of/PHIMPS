package Entity;



import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@AllArgsConstructor
@Entity
@Table(name = "Video")
public class Video {
    @Id
    private String id;

    @Column(name="title", columnDefinition = "nvarchar(50)")
    private String title;
    @Column(name="poster")
    private String poster;
    @Column(name="views")

    private Integer views;

    @Column(name="description", columnDefinition = "nvarchar(50)")
    private String description;
    @Column(name="active")

    private Boolean active;
    @Column(name="code")
    private String code;

    /* relationships */
    @OneToMany(mappedBy = "video")
    List<Favorite> favorites;

    @OneToMany(mappedBy = "video")
    List<Share> shares;

	public void getId(String videoId) {
		// TODO Auto-generated method stub
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Favorite> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}

	public List<Share> getShares() {
		return shares;
	}

	public void setShares(List<Share> shares) {
		this.shares = shares;
	}
}