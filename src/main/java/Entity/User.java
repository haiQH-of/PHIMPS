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
@Table(name = "users")
public class User {
    @Id
    private String id; // Không sử dụng @GeneratedValue

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name="fullname", columnDefinition = "nvarchar(50)")
    private String fullname;

    @Column(name="admin")
    private Boolean admin;

    /* relationships */
    @OneToMany(mappedBy = "user")
    List<Favorite> favorites;

    @OneToMany(mappedBy = "user")
    List<Share> shares;

    // Thêm phương thức kiểm tra quyền admin
    public boolean isAdmin() {
        return Boolean.TRUE.equals(this.admin);
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
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
