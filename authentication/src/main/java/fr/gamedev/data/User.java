package fr.gamedev.data;

import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Swan
 *
 */
@Entity
public class User extends RepresentationModel<User> {

  /**.
   * id long
   * */
  @GeneratedValue
  @Id
  private long id;

  /**.
   * lastname string
   * */
  @Column(unique=true)
  private String lastName;

  /**.
   * password Sting
   * */
  private String password;

  /**.
   * SkillIds skills
   */
  @OneToMany
  private Set<Skill> skills;

  private static Skill longToSkill(Long aLong) {
    return new Skill().setId(aLong);
  }

  /**
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * @param unId the id to set
   */
  public User setId(final long unId) {
    this.id = unId;
    return this;
  }

  /**
   * @return the lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * @param unLastName the lastName to set
   */
  public User setLastName(final String unLastName) {
    this.lastName = unLastName;
    return this;
  }

  /**
   * @return skills
   */
  public Set<Skill> getSkills() {
    return skills;
  }

  public User setSkills(Set<Skill> skills) {
    this.skills = skills;
    return this;
  }

  public User addSkills(Set<Long> skills) {
    Set<Skill> mergeSkills = Stream.concat(
            this.skills.stream(),
            skills.stream().map(User::longToSkill)
    ).collect(Collectors.toSet());

    this.setSkills(mergeSkills);

    return this;
  }

  public User removeSkills(Set<Long> skills) {
    Set<Skill> filteredSkills = this.skills
            .stream()
            .filter(skill -> !skills.contains(skill.id()))
            .collect(Collectors.toSet());

    this.setSkills(filteredSkills);

    return this;
  }

  public String getPassword() {
    return password;
  }

  public User setPassword(String password) {
    this.password = password;
    return this;
  }

}
