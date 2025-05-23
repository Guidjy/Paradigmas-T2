PGDMP  "    6        
        }         
   campeonato    17.4    17.4     ?           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false            @           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false            A           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false            B           1262    24596 
   campeonato    DATABASE     p   CREATE DATABASE campeonato WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en-US';
    DROP DATABASE campeonato;
                     postgres    false            �            1259    24610 	   jogadores    TABLE     �   CREATE TABLE public.jogadores (
    id integer NOT NULL,
    "time" integer NOT NULL,
    nome character varying(50) NOT NULL,
    posicao character varying(50) NOT NULL,
    idade integer NOT NULL,
    numero integer NOT NULL
);
    DROP TABLE public.jogadores;
       public         heap r       postgres    false            �            1259    24651    jogadores_id_seq    SEQUENCE     �   ALTER TABLE public.jogadores ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.jogadores_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public               postgres    false    218            �            1259    24621    partidas    TABLE     �   CREATE TABLE public.partidas (
    id integer NOT NULL,
    numero_da_rodada integer NOT NULL,
    casa integer NOT NULL,
    visitante integer NOT NULL,
    gols_casa integer DEFAULT 0 NOT NULL,
    gols_visitante integer DEFAULT 0 NOT NULL
);
    DROP TABLE public.partidas;
       public         heap r       postgres    false            �            1259    24650    partidas_id_seq    SEQUENCE     �   ALTER TABLE public.partidas ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.partidas_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public               postgres    false    219            �            1259    24600    times    TABLE       CREATE TABLE public.times (
    id integer NOT NULL,
    nome character varying(50) NOT NULL,
    estadio character varying(50) NOT NULL,
    cidade character varying NOT NULL,
    data_de_fundacao date NOT NULL,
    n_jogadores integer DEFAULT 0 NOT NULL
);
    DROP TABLE public.times;
       public         heap r       postgres    false            �            1259    24648    times_id_seq    SEQUENCE     �   ALTER TABLE public.times ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.times_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public               postgres    false    217            �            1259    24638    treinadores    TABLE     �   CREATE TABLE public.treinadores (
    id integer NOT NULL,
    nome character varying(50) NOT NULL,
    "time" integer NOT NULL
);
    DROP TABLE public.treinadores;
       public         heap r       postgres    false            �            1259    24649    treinadores_id_seq    SEQUENCE     �   ALTER TABLE public.treinadores ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.treinadores_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public               postgres    false    220            6          0    24610 	   jogadores 
   TABLE DATA           M   COPY public.jogadores (id, "time", nome, posicao, idade, numero) FROM stdin;
    public               postgres    false    218   �       7          0    24621    partidas 
   TABLE DATA           d   COPY public.partidas (id, numero_da_rodada, casa, visitante, gols_casa, gols_visitante) FROM stdin;
    public               postgres    false    219   �       5          0    24600    times 
   TABLE DATA           Y   COPY public.times (id, nome, estadio, cidade, data_de_fundacao, n_jogadores) FROM stdin;
    public               postgres    false    217           8          0    24638    treinadores 
   TABLE DATA           7   COPY public.treinadores (id, nome, "time") FROM stdin;
    public               postgres    false    220   1        C           0    0    jogadores_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.jogadores_id_seq', 1, false);
          public               postgres    false    224            D           0    0    partidas_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.partidas_id_seq', 1, false);
          public               postgres    false    223            E           0    0    times_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.times_id_seq', 1, false);
          public               postgres    false    221            F           0    0    treinadores_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.treinadores_id_seq', 1, false);
          public               postgres    false    222            �           2606    24606    times id 
   CONSTRAINT     F   ALTER TABLE ONLY public.times
    ADD CONSTRAINT id PRIMARY KEY (id);
 2   ALTER TABLE ONLY public.times DROP CONSTRAINT id;
       public                 postgres    false    217            �           2606    24614    jogadores jogadores_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.jogadores
    ADD CONSTRAINT jogadores_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.jogadores DROP CONSTRAINT jogadores_pkey;
       public                 postgres    false    218            �           2606    24627    partidas partidas_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.partidas
    ADD CONSTRAINT partidas_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.partidas DROP CONSTRAINT partidas_pkey;
       public                 postgres    false    219            �           2606    24642    treinadores treinadores_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.treinadores
    ADD CONSTRAINT treinadores_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.treinadores DROP CONSTRAINT treinadores_pkey;
       public                 postgres    false    220            �           2606    24628    partidas casa    FK CONSTRAINT     i   ALTER TABLE ONLY public.partidas
    ADD CONSTRAINT casa FOREIGN KEY (casa) REFERENCES public.times(id);
 7   ALTER TABLE ONLY public.partidas DROP CONSTRAINT casa;
       public               postgres    false    219    217    4761            �           2606    24615    jogadores time    FK CONSTRAINT     n   ALTER TABLE ONLY public.jogadores
    ADD CONSTRAINT "time" FOREIGN KEY ("time") REFERENCES public.times(id);
 :   ALTER TABLE ONLY public.jogadores DROP CONSTRAINT "time";
       public               postgres    false    4761    218    217            �           2606    24643    treinadores time    FK CONSTRAINT     p   ALTER TABLE ONLY public.treinadores
    ADD CONSTRAINT "time" FOREIGN KEY ("time") REFERENCES public.times(id);
 <   ALTER TABLE ONLY public.treinadores DROP CONSTRAINT "time";
       public               postgres    false    4761    220    217            �           2606    24633    partidas visitante    FK CONSTRAINT     s   ALTER TABLE ONLY public.partidas
    ADD CONSTRAINT visitante FOREIGN KEY (visitante) REFERENCES public.times(id);
 <   ALTER TABLE ONLY public.partidas DROP CONSTRAINT visitante;
       public               postgres    false    4761    219    217            6      x������ � �      7      x������ � �      5      x������ � �      8      x������ � �     