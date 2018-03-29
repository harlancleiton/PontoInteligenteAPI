create table company (
	id bigint(20) NOT NULL,
    cnpj varchar(255) not null,
    creation_date datetime not null,
    update_date datetime not null
) engine=InnoDB default charset=utf8;

create table employees(
	id bigint(20) not null,
    company_id bigint(20) default null,
    cpf varchar(255) not null,
    email varchar(255) not null,
    `password` varchar(255) not null,
   `name` varchar(255) not null,
    `profile` varchar(255) not null,
    hour_value decimal(19,2) default null,
    hour_per_day float default null,
    hour_lunchfloat float default null,
    creation_date datetime not null,
    update_date datetime not null
) engine=InnoDB default charset=utf8;

create table point_releases (
	id bigint(20) not null,
    employees_id bigint(20) not null,
    release_date datetime not null,
    description varchar(255) default null,
    location varchar(255) default null,
    type_release varchar(255) not null,
    creation_date datetime not null,
    update_date datetime not null
)engine=InnoDB default charset=utf8;

alter table company
	add primary key(id);

alter table employees
	add primary key(id),
	add key `FK4cm1kg523jlopyexjbmi6y54j` (company_id);

ALTER TABLE `point_releases`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK46i4k5vl8wah7feutye9kbpi4` (`employees_id`);

--
-- AUTO_INCREMENT for table `empresa`
--
ALTER TABLE `company`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `funcionario`
--
ALTER TABLE `employees`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `lancamento`
--
ALTER TABLE `point_releases`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `funcionario`
--
ALTER TABLE `employees`
  ADD CONSTRAINT `FK4cm1kg523jlopyexjbmi6y54j` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`);

--
-- Constraints for table `lancamento`
--
ALTER TABLE `point_releases`
  ADD CONSTRAINT `FK46i4k5vl8wah7feutye9kbpi4` FOREIGN KEY (`employees_id`) REFERENCES `employees` (`id`);