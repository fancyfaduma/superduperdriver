package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Insert("INSERT into CREDENTIALS(url, username, key, password, userid) " +
            "VALUES (#{url}, #{username}, #{key}, #{password}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty= "credentialId", keyColumn = "credentialid")
    int insert(Credential credential);

    @Select("SELECT * from CREDENTIALS WHERE userid = #{userid}")
    List<Credential> getCredentialsByUser(int userid);

    @Select("SELECT * from CREDENTIALS WHERE credentialId = #{credentialId}")
    Credential getCredential(int credentialId);

    @Delete("DELETE from CREDENTIALS WHERE credentialId = # credentialId}")
    void deleteCredential(int credentialId);

    @Update("UPDATE CREDENTIALS SET url=#{url}, username=#{username}, key=#{key}, password=#{password} WHERE credentialId = #{credentialid}")
    void updateCredential(Credential credential);
}
