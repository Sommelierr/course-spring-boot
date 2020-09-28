package by.s0mmelier.models;

import by.s0mmelier.collections.BookCollection;
import by.s0mmelier.collections.AlcoholCollection;
import by.s0mmelier.collections.MarkCollection;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.PackagePrivate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(	name = "c_user",
		uniqueConstraints = {
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email")
		})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;

	boolean status;
	private boolean blocked;

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "user_id")
	private List<BookCollection> bookCollections = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "user_id")
	private List<AlcoholCollection> alcoholCollections = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "user_id")
	private List<MarkCollection> markCollections = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles",
				joinColumns = @JoinColumn(name = "user_id"),
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "book_likes",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "book_id")
	)
	@JsonIgnore
	List<Book> likeBooks = new ArrayList<>();

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "alcohol_likes",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "alcohol_id")
	)
	@JsonIgnore
	List<Alcohol> likeAlcohols = new ArrayList<>();

	@PrePersist
	public void addPositions() {
		likeBooks.forEach(book -> book.getUsersLike().add(this));
	}

	@PreRemove
	public void removePositions() {
		likeBooks.forEach(position -> position.getUsersLike().remove(this));
	}


	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}
}
