package Entity;



import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
    name = "favoriteLab6",
    uniqueConstraints = { 
        @UniqueConstraint(columnNames = {"userid", "videoid"}) 
    }
)
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Sử dụng kiểu dữ liệu Long cho id (tránh việc sử dụng String)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "videoid", nullable = false)
    private Video video;

    @Temporal(TemporalType.TIMESTAMP)  // Lưu thời gian theo định dạng thời gian đầy đủ
    @Column(name = "likedate", nullable = false)
    private Date likedate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public Date getLikedate() {
		return likedate;
	}

	public void setLikedate(Date likedate) {
		this.likedate = likedate;
	}

    // Getters and Setters (Sử dụng Lombok để tự động tạo các phương thức này)
}

