/* eslint-disable */
import { TypedDocumentNode as DocumentNode } from '@graphql-typed-document-node/core';
export type Maybe<T> = T | null;
export type InputMaybe<T> = Maybe<T>;
export type Exact<T extends { [key: string]: unknown }> = { [K in keyof T]: T[K] };
export type MakeOptional<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]?: Maybe<T[SubKey]> };
export type MakeMaybe<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]: Maybe<T[SubKey]> };
export type MakeEmpty<T extends { [key: string]: unknown }, K extends keyof T> = { [_ in K]?: never };
export type Incremental<T> = T | { [P in keyof T]?: P extends ' $fragmentName' | '__typename' ? T[P] : never };
/** All built-in and custom scalars, mapped to their actual values */
export type Scalars = {
  ID: { input: string; output: string; }
  String: { input: string; output: string; }
  Boolean: { input: boolean; output: boolean; }
  Int: { input: number; output: number; }
  Float: { input: number; output: number; }
};

export type BlogPost = Node & {
  __typename?: 'BlogPost';
  content: Scalars['String']['output'];
  date: Scalars['String']['output'];
  id: Scalars['ID']['output'];
  name: Scalars['String']['output'];
  title: Scalars['String']['output'];
  url: Scalars['String']['output'];
};

export type Mutation = {
  __typename?: 'Mutation';
  createBlogPost: BlogPost;
};


export type MutationCreateBlogPostArgs = {
  content: Scalars['String']['input'];
  name: Scalars['String']['input'];
  title: Scalars['String']['input'];
};

export type Node = {
  id: Scalars['ID']['output'];
};

export type PrivacyPreferences = {
  __typename?: 'PrivacyPreferences';
  allowedDomainLogging?: Maybe<Scalars['Boolean']['output']>;
  allowedRequestLogging?: Maybe<Scalars['Boolean']['output']>;
};

export type Query = {
  __typename?: 'Query';
  getBlogPosts: Array<BlogPost>;
  getUserAvatar: Scalars['String']['output'];
  viewer?: Maybe<ViewerContext>;
};

export type UserInfo = {
  profilePictureURL: Scalars['String']['output'];
};

export type ViewerContext = {
  __typename?: 'ViewerContext';
  locale?: Maybe<Scalars['String']['output']>;
  privacyPreferences?: Maybe<PrivacyPreferences>;
};

export type GraphQlQueryQueryVariables = Exact<{ [key: string]: never; }>;


export type GraphQlQueryQuery = { __typename?: 'Query', getUserAvatar: string };

export type PrivacyPreferencesQueryVariables = Exact<{ [key: string]: never; }>;


export type PrivacyPreferencesQuery = { __typename?: 'Query', viewer?: { __typename?: 'ViewerContext', privacyPreferences?: { __typename?: 'PrivacyPreferences', allowedDomainLogging?: boolean | null, allowedRequestLogging?: boolean | null } | null } | null };


export const GraphQlQueryDocument = {"kind":"Document","definitions":[{"kind":"OperationDefinition","operation":"query","name":{"kind":"Name","value":"GraphQLQuery"},"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"getUserAvatar"}}]}}]} as unknown as DocumentNode<GraphQlQueryQuery, GraphQlQueryQueryVariables>;
export const PrivacyPreferencesDocument = {"kind":"Document","definitions":[{"kind":"OperationDefinition","operation":"query","name":{"kind":"Name","value":"PrivacyPreferences"},"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"viewer"},"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"privacyPreferences"},"selectionSet":{"kind":"SelectionSet","selections":[{"kind":"Field","name":{"kind":"Name","value":"allowedDomainLogging"}},{"kind":"Field","name":{"kind":"Name","value":"allowedRequestLogging"}}]}}]}}]}}]} as unknown as DocumentNode<PrivacyPreferencesQuery, PrivacyPreferencesQueryVariables>;