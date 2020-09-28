package by.s0mmelier.models;

import lombok.*;
import lombok.experimental.PackagePrivate;

import javax.persistence.*;
@Getter
@Setter
@ToString
@NoArgsConstructor
@PackagePrivate
@EqualsAndHashCode
@Entity
@Table(name = "c_role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;

	public Role(ERole role){
		this.name = role;
	}
}