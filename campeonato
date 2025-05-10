--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4
-- Dumped by pg_dump version 17.4

-- Started on 2025-05-10 14:47:00

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4918 (class 1262 OID 24596)
-- Name: campeonato; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE campeonato WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en-US';


ALTER DATABASE campeonato OWNER TO postgres;

\connect campeonato

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 218 (class 1259 OID 24610)
-- Name: jogadores; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jogadores (
    id integer NOT NULL,
    "time" integer NOT NULL,
    nome character varying(50) NOT NULL,
    posicao character varying(50) NOT NULL,
    idade integer NOT NULL,
    numero integer NOT NULL
);


ALTER TABLE public.jogadores OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 24621)
-- Name: partidas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.partidas (
    id integer NOT NULL,
    numero_da_rodada integer NOT NULL,
    casa integer NOT NULL,
    visitante integer NOT NULL,
    gols_casa integer DEFAULT 0 NOT NULL,
    gols_visitante integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.partidas OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 24600)
-- Name: times; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.times (
    id integer NOT NULL,
    nome character varying(50) NOT NULL,
    estadio character varying(50) NOT NULL,
    cidade character varying NOT NULL,
    data_de_fundacao date NOT NULL,
    n_jogadores integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.times OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 24638)
-- Name: treinadores; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.treinadores (
    id integer NOT NULL,
    nome character varying(50) NOT NULL,
    "time" integer NOT NULL
);


ALTER TABLE public.treinadores OWNER TO postgres;

--
-- TOC entry 4757 (class 2606 OID 24606)
-- Name: times id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.times
    ADD CONSTRAINT id PRIMARY KEY (id);


--
-- TOC entry 4759 (class 2606 OID 24614)
-- Name: jogadores jogadores_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jogadores
    ADD CONSTRAINT jogadores_pkey PRIMARY KEY (id);


--
-- TOC entry 4761 (class 2606 OID 24627)
-- Name: partidas partidas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.partidas
    ADD CONSTRAINT partidas_pkey PRIMARY KEY (id);


--
-- TOC entry 4763 (class 2606 OID 24642)
-- Name: treinadores treinadores_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.treinadores
    ADD CONSTRAINT treinadores_pkey PRIMARY KEY (id);


--
-- TOC entry 4765 (class 2606 OID 24628)
-- Name: partidas casa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.partidas
    ADD CONSTRAINT casa FOREIGN KEY (casa) REFERENCES public.times(id);


--
-- TOC entry 4764 (class 2606 OID 24615)
-- Name: jogadores time; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jogadores
    ADD CONSTRAINT "time" FOREIGN KEY ("time") REFERENCES public.times(id);


--
-- TOC entry 4767 (class 2606 OID 24643)
-- Name: treinadores time; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.treinadores
    ADD CONSTRAINT "time" FOREIGN KEY ("time") REFERENCES public.times(id);


--
-- TOC entry 4766 (class 2606 OID 24633)
-- Name: partidas visitante; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.partidas
    ADD CONSTRAINT visitante FOREIGN KEY (visitante) REFERENCES public.times(id);


-- Completed on 2025-05-10 14:47:01

--
-- PostgreSQL database dump complete
--

